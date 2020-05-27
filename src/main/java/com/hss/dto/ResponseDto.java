package com.hss.dto;

import com.github.pagehelper.Page;

/**
 * 代码描述
 * FileName:
 *
 * @author: Dckj_Lxh
 * @date: 2018/11/17 15:16
 * @mdate: 2018/11/17 15:16
 * @Modified By:
 * Copyright (c) 2008-2018 鼎昌科技 All Rights Reserved.
 */
public class ResponseDto {
	private int code = 0;
	private String msg = "操作成功";
	private Object data;
	private long count;

	public ResponseDto pageBuild(Page page) {
		this.setCount(page.getTotal());
		this.setData(page.getResult());
		return this;
	}

	ResponseDto(final int code, final String msg, final Object data, final long count) {
		this.code = code;
		this.msg = msg;
		this.data = data;
		this.count = count;
	}

	public static <T> ResponseDto.ResponseDtoBuilder<T> builder() {
		return new ResponseDto.ResponseDtoBuilder();
	}

	private ResponseDto() {
	}

	public int getCode() {
		return this.code;
	}

	public String getMsg() {
		return this.msg;
	}

	public Object getData() {
		return this.data;
	}

	public long getCount() {
		return this.count;
	}

	public void setCode(final int code) {
		this.code = code;
	}

	public void setMsg(final String msg) {
		this.msg = msg;
	}

	public void setData(final Object data) {
		this.data = data;
	}

	public void setCount(final long count) {
		this.count = count;
	}

//	public boolean equals(final Object o) {
//		if (o == this) {
//			return true;
//		} else if (!(o instanceof ResponseDto)) {
//			return false;
//		} else {
//			ResponseDto<?> other = (ResponseDto)o;
//			if (!other.canEqual(this)) {
//				return false;
//			} else if (this.getCode() != other.getCode()) {
//				return false;
//			} else {
//				label41: {
//					Object this$msg = this.getMsg();
//					Object other$msg = other.getMsg();
//					if (this$msg == null) {
//						if (other$msg == null) {
//							break label41;
//						}
//					} else if (this$msg.equals(other$msg)) {
//						break label41;
//					}
//
//					return false;
//				}
//
//				Object this$data = this.getData();
//				Object other$data = other.getData();
//				if (this$data == null) {
//					if (other$data != null) {
//						return false;
//					}
//				} else if (!this$data.equals(other$data)) {
//					return false;
//				}
//
//				if (this.getCount() != other.getCount()) {
//					return false;
//				} else {
//					return true;
//				}
//			}
//		}
//	}
//
//	protected boolean canEqual(final Object other) {
//		return other instanceof ResponseDto;
//	}
//
//	public int hashCode() {
//		int PRIME = true;
//		int result = 1;
//		int result = result * 59 + this.getCode();
//		Object $msg = this.getMsg();
//		result = result * 59 + ($msg == null ? 43 : $msg.hashCode());
//		Object $data = this.getData();
//		result = result * 59 + ($data == null ? 43 : $data.hashCode());
//		long $count = this.getCount();
//		result = result * 59 + (int)($count >>> 32 ^ $count);
//		return result;
//	}

	public String toString() {
		return "ResponseDto(code=" + this.getCode() + ", msg=" + this.getMsg() + ", data=" + this.getData() + ", count=" + this.getCount() + ")";
	}

	public static class ResponseDtoBuilder<T> {
		private int code;
		private String msg;
		private Object data;
		private long count;

		ResponseDtoBuilder() {
		}

		public ResponseDto.ResponseDtoBuilder<T> code(final int code) {
			this.code = code;
			return this;
		}

		public ResponseDto.ResponseDtoBuilder<T> msg(final String msg) {
			this.msg = msg;
			return this;
		}

		public ResponseDto.ResponseDtoBuilder<T> data(final Object data) {
			this.data = data;
			return this;
		}

		public ResponseDto.ResponseDtoBuilder<T> count(final long count) {
			this.count = count;
			return this;
		}

		public ResponseDto build() {
			return new ResponseDto(this.code, this.msg, this.data, this.count);
		}

		public String toString() {
			return "ResponseDto.ResponseDtoBuilder(code=" + this.code + ", msg=" + this.msg + ", data=" + this.data + ", count=" + this.count + ")";
		}
	}
}
