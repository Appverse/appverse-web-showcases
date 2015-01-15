/*
 Copyright (c) 2012 GFT Appverse, S.L., Sociedad Unipersonal.

 This Source Code Form is subject to the terms of the GNU Lesser General Public License (LGPL), 
 version 2.1. If a copy of the MPL was not distributed with this 
 file, You can obtain one at https://www.gnu.org/licenses/lgpl-2.1.html. 

 Redistribution and use in source and binary forms, with or without modification, 
 are permitted provided that the conditions of the GNU Lesser General Public License (LGPL) 
 version 2.1 are met.
 
 This project includes the original Liferay Portlet Bridge with PrimeFaces4 example that can
 be found here: https://github.com/liferay/liferay-faces/tree/master/demos/bridge/primefaces4-portlet
 and it is released under GNU Lesser General Public License (LGPL) version 2.1. and 
 extends it with it own source code. For this reason in the original Liferay code the original packages
 have been kept and the original Liferay header as well. Appverse header have been added so both 
 the orginal one (Liferay) and Appverse can be found in some source files.
  

 THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 DISCLAIMED. EXCEPT IN CASE OF WILLFUL MISCONDUCT OR GROSS NEGLIGENCE, IN NO EVENT
 SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT,
 INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT(INCLUDING NEGLIGENCE OR OTHERWISE) 
 ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE 
 POSSIBILITY OF SUCH DAMAGE.
 */

/**
 * Copyright (c) 2000-2014 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */
package com.liferay.faces.demos.dto;

import java.io.Serializable;


/**
 * This is a bean that represents a Province, and implements the Transfer Object (formerly known as ValueObject/VO)
 * design pattern.
 *
 * @author  "Neil Griffin"
 */
public class Province implements Serializable {

	// serialVersionUID
	private static final long serialVersionUID = 3055995992262228819L;

	// JavaBean Properties
	private long provinceId;
	private String provinceName;

	public Province(long provinceId, String provinceName) {
		this.provinceId = provinceId;
		this.provinceName = provinceName;
	}

	public long getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(long provinceId) {
		this.provinceId = provinceId;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
}
