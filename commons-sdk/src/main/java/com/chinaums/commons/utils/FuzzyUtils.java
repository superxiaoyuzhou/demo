package com.chinaums.commons.utils;

import com.google.common.base.Strings;

/**
 * @author Administrator
 */
public class FuzzyUtils {

	/**
     * [中文姓名] 只显示第一个汉字，其他隐藏星号<例子：李**>
	 *
	 * @param name
	 * @return
	 */
	public static String personName(final String name) {
		return replace(name, 1, 0);
	}

	/**
	 * [固定电话]  前三位，其他用星号隐藏每位1个星号例子:629*****>
	 *
	 * @param telePhone
	 * @return
	 */
	public static String telePhone(final String telePhone) {
		return replace(telePhone, 3, 0);
	}

	/**
	 * [手机号码]  前三位，后四位，其他用星号隐藏每位1个星号例子:152***0420>
	 *
	 * @param phone
	 * @return
	 */
	public static String mobilePhone(final String phone) {
		return replace(phone, 3, 4);
	}

	/**
	 * [银行卡号] 前六位，后四位，其他用星号隐藏每位1个星号<例子:622260**********1234>
	 *
	 * @param cardNo
	 * @return
	 */
	public static String bankCard(final String cardNo) {
		return replace(cardNo, 6, 4);
	}

	/**
	 * [身份证号]
	 * 十八位身份证：前十位，后四位，其他用星号隐藏每位1个星号<例子:5101021992****1234>
     * 十五位身份证：前八位，后三位，其他用星号隐藏每位1个星号<例子:51010292****123>
	 * @param certifId
	 * @return
	 */
	public static String certifId(final String certifId) {
		int startLenth = 10;
		int endLenth = 4;
		if(certifId != null && certifId.length() == 15){
			startLenth = 8;
			endLenth = 3;
		}
		return replace(certifId, startLenth, endLenth);
	}

	public static String replace(final String data, final int startLength, final int endLength) {
		if (Strings.isNullOrEmpty(data) || data.length() <= endLength) {
			return data;
		}
		final int length = data.length();
		final int resvCount = startLength + endLength;
		final int startCount = resvCount < length ? length - resvCount : length - endLength;
		final int startLengthActual = Math.max(length - endLength - startCount, 0);
		final String paddingString = Strings.repeat("*", startCount);
		final String prefix = data.substring(0, startLengthActual);
		final String suffix = data.substring(startLengthActual + startCount, length);
		return prefix + paddingString + suffix;
	}

	public static void main(String[] args) {
		System.out.println(personName("13490022123"));
	}
}