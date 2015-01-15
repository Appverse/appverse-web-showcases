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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.faces.FacesWrapper;

import com.liferay.faces.bridge.model.UploadedFile;
import com.liferay.faces.bridge.renderkit.primefaces.FileUploadRendererPrimeFacesImpl;
import com.liferay.faces.util.logging.Logger;
import com.liferay.faces.util.logging.LoggerFactory;


/**
 * @author  Neil Griffin
 */
public class UploadedFileWrapper implements Serializable, UploadedFile,
	FacesWrapper<org.primefaces.model.UploadedFile> {

	// serialVersionUID
	private static final long serialVersionUID = 5347502272011734546L;

	// Logger
	private static final Logger logger = LoggerFactory.getLogger(UploadedFileWrapper.class);

	// Private Data Members
	private Map<String, Object> attributeMap;
	private String id;
	private File file;
	private Status status;
	private org.primefaces.model.UploadedFile wrappedUploadedFile;

	public UploadedFileWrapper(org.primefaces.model.UploadedFile uploadedFile, UploadedFile.Status status,
		String uniqueFolderName) {
		this.wrappedUploadedFile = uploadedFile;
		this.status = status;
		this.attributeMap = new HashMap<String, Object>();
		this.id = Long.toString(((long) hashCode()) + System.currentTimeMillis());
		this.file = getFile(uniqueFolderName);
	}

	public void delete() throws IOException {

		if (file != null) {
			file.delete();
		}
	}

	public void write(String fileName) throws IOException {
		OutputStream outputStream = new FileOutputStream(fileName);
		outputStream.write(getBytes());
		outputStream.close();
	}

	public String getAbsolutePath() {
		String absolutePath = null;

		if (file != null) {
			absolutePath = file.getAbsolutePath();
		}

		return absolutePath;
	}

	public Map<String, Object> getAttributes() {
		return attributeMap;
	}

	public byte[] getBytes() throws IOException {

		byte[] bytes = null;

		if ((file != null) && (file.exists())) {
			RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");
			bytes = new byte[(int) randomAccessFile.length()];
			randomAccessFile.readFully(bytes);
			randomAccessFile.close();
		}

		return bytes;
	}

	public String getCharSet() {
		throw new UnsupportedOperationException();
	}

	public String getContentType() {
		return wrappedUploadedFile.getContentType();
	}

	/**
	 * Since the PrimeFaces UploadedFile interface does not provide a method for deleting the file, Liferay Faces Bridge
	 * automatically deletes it when the wrappedUploadedFile.getContents() method is called. For more information, see
	 * {@link FileUploadRendererPrimeFacesImpl.PrimeFacesFileItem#get()} and {@link
	 * FileUploadRendererPrimeFacesImpl.UploadedFileInputStream#close()}.
	 */
	protected File getFile(String uniqueFolderName) {

		File file = null;

		try {
			File tempFolder = new File(System.getProperty("java.io.tmpdir"));
			File uniqueFolder = new File(tempFolder, uniqueFolderName);

			if (!uniqueFolder.exists()) {
				uniqueFolder.mkdirs();
			}

			String fileNamePrefix = "uploadedFile" + getId();
			String fileNameSuffix = ".dat";
			file = File.createTempFile(fileNamePrefix, fileNameSuffix, uniqueFolder);

			OutputStream outputStream = new FileOutputStream(file);
			outputStream.write(wrappedUploadedFile.getContents());
			outputStream.close();

			// Temporary file maintained by PrimeFaces is automatically deleted. See JavaDoc comments above.
		}
		catch (Exception e) {
			logger.error(e);
		}

		return file;
	}

	public String getHeader(String name) {
		throw new UnsupportedOperationException();
	}

	public Collection<String> getHeaderNames() {
		throw new UnsupportedOperationException();
	}

	public Collection<String> getHeaders(String name) {
		throw new UnsupportedOperationException();
	}

	public String getId() {
		return id;
	}

	public InputStream getInputStream() throws IOException {
		return new FileInputStream(file);
	}

	public String getMessage() {
		throw new UnsupportedOperationException();
	}

	public String getName() {
		return wrappedUploadedFile.getFileName();
	}

	public long getSize() {
		return wrappedUploadedFile.getSize();
	}

	public Status getStatus() {
		return status;
	}

	public org.primefaces.model.UploadedFile getWrapped() {
		return wrappedUploadedFile;
	}
}
