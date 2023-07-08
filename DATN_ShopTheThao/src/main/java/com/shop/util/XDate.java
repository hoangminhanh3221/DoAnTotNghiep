package com.shop.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Service;

@Service
public class XDate {
	public String convertToPattern(Date yourdate, String pattern) {
		return new SimpleDateFormat(pattern).format(yourdate);
	}
}
