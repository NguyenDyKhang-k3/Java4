package com.fpoly.util;

import java.security.SecureRandom;
import java.util.Random;

public class RandomStringGenerator {
	private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
	private static final String DIGITS = "0123456789";
	private static final String[] FIRST_NAMES = { "Nguyễn", "Trần", "Lê", "Phạm", "Hoàng", "Huỳnh", "Vũ", "Đặng", "Bùi",
			"Đỗ", "Hồ", "Ngô" };
	private static final String[] LAST_NAMES = { "Huy", "Nam", "Linh", "Trang", "Bình", "Anh", "Thủy", "Mai", "Đức",
			"Thành", "Quỳnh", "Tùng" };
	private static final SecureRandom random = new SecureRandom();

	public static String generateRandomString(int length) {
		StringBuilder sb = new StringBuilder(length);
		for (int i = 0; i < length; i++) {
			int randomIndex = random.nextInt(CHARACTERS.length());
			char randomChar = CHARACTERS.charAt(randomIndex);
			sb.append(randomChar);
		}
		return sb.toString();
	}

	public static String generateRandomPhoneNumber(int length) {
		StringBuilder sb = new StringBuilder(length);
		for (int i = 0; i < length; i++) {
			int randomIndex = random.nextInt(DIGITS.length());
			char randomDigit = DIGITS.charAt(randomIndex);
			sb.append(randomDigit);
		}
		return sb.toString();
	}

	public static String generateUsername() {
		String[] prefixes = { "user", "customer", "admin", "testuser", "guest", "developer" };
		Random random = new Random();

		String prefix = prefixes[random.nextInt(prefixes.length)];
		String suffix = generateRandomString(5); // Độ dài của phần tử cuối username

		return prefix + "_" + suffix;
	}

	// Hàm để tạo chuỗi ngẫu nhiên
	private static String generateRandomUsername(int length) {
		String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		Random random = new Random();
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < length; i++) {
			int index = random.nextInt(characters.length());
			char randomChar = characters.charAt(index);
			sb.append(randomChar);
		}

		return sb.toString();
	}

	public static String generateRandomVietnameseName() {
		String firstName = FIRST_NAMES[random.nextInt(FIRST_NAMES.length)];
		String lastName = LAST_NAMES[random.nextInt(LAST_NAMES.length)];
		return firstName + " " + lastName;
	}
}