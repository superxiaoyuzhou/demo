package com.piter.fastjson.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.piter.fastjson.extend.fastjson.AmountDeserializer;
import com.piter.fastjson.extend.fastjson.AmountSerializer;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;

/**
 * @author Piter
 */
@Data
public class BannerAddDTO{

    @Digits(integer = 16,fraction = 2)
    @JSONField(serializeUsing = AmountSerializer.class,deserializeUsing = AmountDeserializer.class)
    private BigDecimal tradeAmount;
}
