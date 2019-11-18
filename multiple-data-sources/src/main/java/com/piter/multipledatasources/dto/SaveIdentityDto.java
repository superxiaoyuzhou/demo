package com.piter.multipledatasources.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;


@Data
@Accessors(chain = true)
public class SaveIdentityDto implements Serializable {

  private String name;

  private String issuOrg;

  private String validBegin;

  private String validEnd;

  private String nation;

  private String address;

  private String frontFileId;

  private String backFileId;


}
