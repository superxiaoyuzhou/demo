#!/bin/env bash
#author by wanglin.

# Source function library.
. /etc/rc.d/init.d/functions

set -u

COLUMNS=1
RETVAL=0

#define 启动项目shell脚本 variables
#configfile="$HOME/启动项目shell脚本.json"
#programs=($(jq -r '.programs[]|.name' ${configfile}))
configfile="$HOME/启动项目shell脚本.txt"
programs="$(awk  'BEGIN{FS="|"}{print $1}' ${configfile})"
dependence_prog=(discovery config-center)
exclude_prog=(discovery config-center)


get_prog_var(){
  #[ 1 ] json format variables
  if [[ ${configfile} == "$HOME/启动项目shell脚本.json" ]];then
     prog_log="$HOME/$(jq -r '.prog_log' ${configfile})"
     deploy_path="$HOME/$(jq -r '.deploy_path' ${configfile})"
     backup_path="$HOME/$(jq -r '.backup_path' ${configfile})"
     build_root="$(jq -r '.build_root' ${configfile})"
     ssh_uri="$(jq -r '.ssh_uri' ${configfile})"

     prog_path="$HOME/$(jq  -r ".programs[] | select(.name == \"${1}\")|.prog_path" ${configfile})"
     spring_args="$(jq  -r ".programs[] | select(.name == \"${1}\")|.spring_args" ${configfile})"
     jvm_args="$(jq  -r ".programs[] | select(.name == \"${1}\")|.jvm_args" ${configfile})"
     build_path="$(jq  -r ".programs[] | select(.name == \"${1}\")|.build_path" ${configfile})"

  #[ 2 ] txt format variables
  elif [[ ${configfile} == "$HOME/启动项目shell脚本.txt" ]];then
    prog_log="$HOME/logs"
    deploy_path="$HOME/启动项目shell脚本"
    backup_path="$HOME/backup"
    build_root="/var/lib/jenkins/workspace"
    ssh_uri="jenkins@172.16.208.245"

    prog_path="$HOME/apps"
    jvm_args="$(awk 'BEGIN{FS="|"}{gsub(/[[:space:]]/,"",$1)}$1=="'${1}'"{print $2}' ${configfile})"
    spring_args="$(awk 'BEGIN{FS="|"}{gsub(/[[:space:]]/,"",$1)}$1=="'${1}'"{print $3}' ${configfile})"
    build_path="$(awk 'BEGIN{FS="|"}{gsub(/[[:space:]]/,"",$1)}$1=="'${1}'"{print $4}' ${configfile})"
  else
    echo "configfile is not found."
  fi

  for filename in ${prog_path} ${prog_log} ${deploy_path} ${backup_path}
  do
    [[ -d ${filename} ]] || mkdir ${filename}
  done
}

main() {
  PS3="please select your action[num]:"
  actions=(exit start stop restart 启动项目shell脚本 rollback)
  select action in ${actions[@]}
  do 
    case ${action} in
      start|stop|restart|启动项目shell脚本|rollback)
        project
        break
        ;;
      exit)
        break
        ;;                  
      *)
        echo "invalid choice"
        ;;
    esac
  done
}

project() {
  PS3="please select your project[num]:"
  select prog in exit "${action}_all" ${programs[@]};
  do
    if [[ ${prog} == "exit" ]];then
      break
    elif [[ ${prog} == "${action}_all" ]];then
      for name in ${programs[@]}
        do
          if [[ "${exclude_prog[@]}" =~ "${name}" ]];then
            continue
          fi
          get_prog_var ${name}
          $action ${name}
          sleep 5
        done
        break
    else
      get_prog_var ${prog}
      ${action} ${prog}
    fi    
  done
}

start() {
  if [[ -f  ${prog_path}/${1}.jar ]];then 
    echo -n $"Starting ${1}: "
    echo -n $" nohup /usr/local/java/jdk1.8.0_77/bin/java ${jvm_args} -jar ${1}.jar ${spring_args} >> ${prog_log}/${1}.log  2>&1 & "
    cd ${prog_path}
    nohup /usr/local/java/jdk1.8.0_77/bin/java ${jvm_args} -jar ${1}.jar ${spring_args} >> ${prog_log}/${1}.log  2>&1 &
    echo "$!" > ${1}.pid
    RETVAL=$?
    [[ $RETVAL = 0 ]] && echo $"[  OK  ]"
  else
    echo "${1} is not exist."
  fi
}

stop() {
  if [[ -n ${prog_path}/${1}.pid ]];then
    echo -n $"Stopping ${1}: "
    killproc -p ${prog_path}/${1}.pid -d 30 ${1}
    RETVAL=$?
    echo
    [[ $RETVAL=0 ]] && rm -f ${prog_path}/${1}.pid
  else
    echo "${1} is not running."
  fi
}

restart() {
  stop "$1"
  sleep 5
  start "$1"
}

启动项目shell脚本() {
  #scp ${ssh_uri}:${build_root}/${build_path} ${deploy_path}/${1}.jar
  if [[ -f ${deploy_path}/${1}.jar ]];then
    [[ -f ${prog_path}/${1}.jar ]] && mv ${prog_path}/${1}.jar ${backup_path}/${1}.jar.$(date +%Y%m%d_%s) 
    stop ${1}
    mv ${deploy_path}/${1}.jar ${prog_path}/${1}.jar
    start ${1}
  else
    echo "启动项目shell脚本 package ${1} not found."
  fi
}

rollback() {
  PS3="please select the rollback file[num]:" 
  select rollfile in exit $(find ${backup_path} -name ${1}.jar* -exec basename {} \;)
  do
    if [[ ${rollfile} == "exit" ]];then
      exit
    else
      cp ${backup_path}/${rollfile} ${deploy_path}/${1}.jar
      启动项目shell脚本 ${1}
    fi
  done
}

auto_start(){
  for dname in ${dependence_prog[@]}
  do
    get_prog_var ${dname}
    start ${dname}
    sleep 30
  done

  for name in ${programs[@]}
  do
    if [[ "${exclude_prog[@]}" =~ "${name}" ]];then
      continue
    fi
    get_prog_var ${name}
    start ${name}
    sleep 5
  done
}

auto_deploy(){
  for name in ${programs[@]}
  do
    if [[ "${exclude_prog[@]}" =~ "${name}" ]];then
      continue
    fi
    get_prog_var ${name}
    启动项目shell脚本 ${name}
    sleep 5
  done
}

if [[ $# -eq 1 && "${1}" == "auto_start" ]];then
    auto_start
elif [[ $# -eq 1 && "${1}" == "auto_deploy" ]];then
    auto_deploy
else
  main
fi
