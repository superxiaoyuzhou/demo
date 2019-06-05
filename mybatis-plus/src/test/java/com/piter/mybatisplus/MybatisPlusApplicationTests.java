package com.piter.mybatisplus;

import com.baomidou.mybatisplus.core.toolkit.SerializationUtils;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.piter.mybatisplus.entity.Banner;
import com.piter.mybatisplus.entity.SerializedLambda;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamClass;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
//这就是MybatisPlus底层的实现，我只是把它单独拿出来了
public class MybatisPlusApplicationTests {

    @Test
    public void test() {
        SerializedLambda serializedLambda = getSerializedLambda(Banner::getBannerName);
        System.out.println(serializedLambda.getImplMethodName());  //输出结果为：getBannerName,再做的个简单的字符串截取即可获得bannerName属性名
    }

    public static SerializedLambda getSerializedLambda(SFunction<Banner, ?> lambda) {
        SerializedLambda serializedLambda = null;
        try {
            ObjectInputStream objIn = new ObjectInputStream(new ByteArrayInputStream(serialize(lambda)))
            {
                protected Class<?> resolveClass(ObjectStreamClass objectStreamClass) throws IOException, ClassNotFoundException {
                    Class<?> clazz = super.resolveClass(objectStreamClass);
                    System.out.println(clazz);
                    return clazz == java.lang.invoke.SerializedLambda.class ? SerializedLambda.class : clazz;
                }
            };
            serializedLambda  = (SerializedLambda) objIn.readObject();
        } catch (Exception e) {
            System.err.println(e);
        }
        return serializedLambda;
    }

    public static byte[] serialize(Object object) {
        if (object == null) {
            return null;
        } else {
            ByteArrayOutputStream baos = new ByteArrayOutputStream(1024);
            try {
                ObjectOutputStream oos = new ObjectOutputStream(baos);
                oos.writeObject(object);
                oos.flush();
            } catch (IOException var3) {
                throw new IllegalArgumentException("Failed to serialize object of type: " + object.getClass(), var3);
            }

            return baos.toByteArray();
        }
    }

    @Test
    public void test1() throws IOException {
        Banner banner = new Banner(){
            public String getBannerCode(){
                return "aaa";
            }
        };
        System.out.println(banner.getBannerCode());
    }
}
