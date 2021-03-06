package com.hss.base;

import java.lang.management.ManagementFactory;
import java.net.InetAddress;
import java.net.NetworkInterface;

/**
 * 代码描述
 * FileName:
 *
 * @author: Dckj_Lxh
 * @date: 2018/11/16 16:55
 * @mdate: 2018/11/16 16:55
 * @Modified By:
 * Copyright (c) 2008-2018 鼎昌科技 All Rights Reserved.
 */
public class IdWorker {
	private static final long twepoch = 1514338236397L;
	private static final long workerIdBits = 5L;
	private static final long datacenterIdBits = 5L;
	private static final long maxWorkerId = 31L;
	private static final long maxDatacenterId = 31L;
	private static final long sequenceBits = 12L;
	private static final long workerIdShift = 12L;
	private static final long datacenterIdShift = 17L;
	private static final long timestampLeftShift = 22L;
	private static final long sequenceMask = 4095L;
	private static long lastTimestamp = -1L;
	private long sequence = 0L;
	private final long workerId;
	private final long datacenterId;
	public static final IdWorker id = new IdWorker();

	public IdWorker() {
		this.datacenterId = getDatacenterId(31L);
		this.workerId = getMaxWorkerId(this.datacenterId, 31L);
	}

	public IdWorker(long workerId, long datacenterId) {
		if (workerId <= 31L && workerId >= 0L) {
			if (datacenterId <= 31L && datacenterId >= 0L) {
				this.workerId = workerId;
				this.datacenterId = datacenterId;
			} else {
				throw new IllegalArgumentException(String.format("datacenter Id can't be greater than %d or less than 0", 31L));
			}
		} else {
			throw new IllegalArgumentException(String.format("worker Id can't be greater than %d or less than 0", 31L));
		}
	}

	public synchronized long nextId() {
		long timestamp = this.timeGen();
		if (timestamp < lastTimestamp) {
			throw new RuntimeException(String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds", lastTimestamp - timestamp));
		} else {
			if (lastTimestamp == timestamp) {
				this.sequence = this.sequence + 1L & 4095L;
				if (this.sequence == 0L) {
					timestamp = this.tilNextMillis(lastTimestamp);
				}
			} else {
				this.sequence = 0L;
			}

			lastTimestamp = timestamp;
			long nextId = timestamp - 1514338236397L << 22 | this.datacenterId << 17 | this.workerId << 12 | this.sequence;
			return nextId;
		}
	}

	private long tilNextMillis(final long lastTimestamp) {
		long timestamp;
		for(timestamp = this.timeGen(); timestamp <= lastTimestamp; timestamp = this.timeGen()) {
			;
		}

		return timestamp;
	}

	private long timeGen() {
		return System.currentTimeMillis();
	}

	protected static long getMaxWorkerId(long datacenterId, long maxWorkerId) {
		StringBuffer mpid = new StringBuffer();
		mpid.append(datacenterId);
		String name = ManagementFactory.getRuntimeMXBean().getName();
		if (!name.isEmpty()) {
			mpid.append(name.split("@")[0]);
		}

		return (long)(mpid.toString().hashCode() & '\uffff') % (maxWorkerId + 1L);
	}

	protected static long getDatacenterId(long maxDatacenterId) {
		long id = 0L;

		try {
			InetAddress ip = InetAddress.getLocalHost();
			NetworkInterface network = NetworkInterface.getByInetAddress(ip);
			if (network == null) {
				id = 1L;
			} else {
				byte[] mac = network.getHardwareAddress();
				id = (255L & (long)mac[mac.length - 1] | 65280L & (long)mac[mac.length - 2] << 8) >> 6;
				id %= maxDatacenterId + 1L;
			}
		} catch (Exception var7) {
			System.out.println(" getDatacenterId: " + var7.getMessage());
		}

		return id;
	}

	public static void main(String[] args) {
		System.out.println(System.currentTimeMillis());

		for(int i = 0; i < 100000; ++i) {
			System.out.println(id.nextId());
		}

	}
}
