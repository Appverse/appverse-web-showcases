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
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import com.liferay.faces.demos.dto.City;
import com.liferay.faces.demos.dto.Province;


/**
 * @author  Neil Griffin
 */
@ApplicationScoped
@ManagedBean(name = "listModelBean")
public class ListModelBean implements Serializable {

	// serialVersionUID
	private static final long serialVersionUID = 4433667773050051612L;

	// Private Data Members
	private List<City> cities;
	private List<Province> provinces;

	public List<City> getCities() {

		if (cities == null) {
			long cityId = 1;
			cities = new ArrayList<City>();

			City city = new City(cityId++, getProvinceId("DE"), "Wilmington", "19806");
			cities.add(city);
			city = new City(cityId++, getProvinceId("GA"), "Atlanta", "30329");
			cities.add(city);
			city = new City(cityId++, getProvinceId("FL"), "Orlando", "32801");
			cities.add(city);
			city = new City(cityId++, getProvinceId("MD"), "Baltimore", "21224");
			cities.add(city);
			city = new City(cityId++, getProvinceId("NC"), "Charlotte", "28202");
			cities.add(city);
			city = new City(cityId++, getProvinceId("NJ"), "Hoboken", "07030");
			cities.add(city);
			city = new City(cityId++, getProvinceId("NY"), "Albany", "12205");
			cities.add(city);
			city = new City(cityId++, getProvinceId("SC"), "Columbia", "29201");
			cities.add(city);
			city = new City(cityId++, getProvinceId("VA"), "Roanoke", "24013");
			cities.add(city);
		}

		return cities;
	}

	public City getCityByPostalCode(String postalCode) {
		List<City> cities = getCities();

		for (City city : cities) {

			if (city.getPostalCode().equals(postalCode)) {
				return city;
			}
		}

		return null;
	}

	public long getProvinceId(String provinceName) {
		long provinceId = 0;
		List<Province> provinces = getProvinces();

		for (Province province : provinces) {

			if (province.getProvinceName().equals(provinceName)) {
				provinceId = province.getProvinceId();

				break;
			}
		}

		return provinceId;
	}

	public List<Province> getProvinces() {

		if (provinces == null) {
			long provinceId = 1;
			provinces = new ArrayList<Province>();

			Province province = new Province(provinceId++, "DE");
			provinces.add(province);
			province = new Province(provinceId++, "GA");
			provinces.add(province);
			province = new Province(provinceId++, "FL");
			provinces.add(province);
			province = new Province(provinceId++, "MD");
			provinces.add(province);
			province = new Province(provinceId++, "NC");
			provinces.add(province);
			province = new Province(provinceId++, "NJ");
			provinces.add(province);
			province = new Province(provinceId++, "NY");
			provinces.add(province);
			province = new Province(provinceId++, "SC");
			provinces.add(province);
			province = new Province(provinceId++, "VA");
			provinces.add(province);
		}

		return this.provinces;
	}
}
