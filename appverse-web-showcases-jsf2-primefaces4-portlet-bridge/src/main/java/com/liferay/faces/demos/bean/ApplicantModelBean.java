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
package com.liferay.faces.demos.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.liferay.faces.bridge.model.UploadedFile;


/**
 * This is a model managed bean that represents an applicant that is applying for a job.
 *
 * @author  "Neil Griffin"
 */
@ManagedBean(name = "applicantModelBean")
@ViewScoped
public class ApplicantModelBean implements Serializable {

	// serialVersionUID
	private static final long serialVersionUID = 7459628254337818761L;

	// Private Data Members
	private List<UploadedFile> uploadedFiles;
	private String city;
	private String comments;
	private Date dateOfBirth;
	private String emailAddress;
	private String firstName;
	private String lastName;
	private String phoneNumber;
	private String postalCode;
	private Long provinceId;

	// Private Data Members (auto-fill)
	private String autoFillCity;
	private Long autoFillProvinceId;

	public ApplicantModelBean() {
		clearProperties();
		this.dateOfBirth = Calendar.getInstance().getTime();
	}

	public void clearProperties() {
		uploadedFiles = new ArrayList<UploadedFile>();
		city = null;
		comments = null;
		dateOfBirth = null;
		emailAddress = null;
		firstName = null;
		lastName = null;
		phoneNumber = null;
		postalCode = null;
		provinceId = null;
	}

	public void setAutoFillCity(String autoFillCity) {
		this.autoFillCity = autoFillCity;
	}

	public void setAutoFillProvinceId(Long autoFillProvinceId) {
		this.autoFillProvinceId = autoFillProvinceId;
	}

	public String getCity() {

		if (autoFillCity == null) {
			return city;
		}
		else {
			return autoFillCity;
		}
	}

	public void setCity(String city) {

		if (autoFillCity == null) {
			this.city = city;
		}
		else {
			this.city = autoFillCity;
			autoFillCity = null;
		}
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public Long getProvinceId() {

		if (autoFillProvinceId == null) {
			return provinceId;
		}
		else {
			return autoFillProvinceId;
		}
	}

	public void setProvinceId(Long provinceId) {

		if (autoFillProvinceId == null) {
			this.provinceId = provinceId;
		}
		else {
			this.provinceId = autoFillProvinceId;
			autoFillProvinceId = null;
		}
	}

	public List<UploadedFile> getUploadedFiles() {
		return uploadedFiles;
	}

}
