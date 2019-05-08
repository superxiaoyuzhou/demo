package com.piter.hikaricptest.entity;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 'testdb.mtview' is not BASE TABLE
 * </p>
 *
 * @author jobob
 * @since 2019-04-26
 */
@Data
@Accessors(chain = true)
public class Table1 {

private static final long serialVersionUID=1L;

    private String name;

    private Integer age;


}
