package com.xy.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class WriteToDisk {
	InputStream in;
	String name;

	public WriteToDisk(InputStream in, String name) {
		this.in = in;
		this.name = name;
	}

	public boolean doWrite() {
		File file = new File("D:\\BSData\\" +name);

		BufferedInputStream bufin = null;
		BufferedOutputStream bufout = null;
		try {
			bufin = new BufferedInputStream(in);
			bufout = new BufferedOutputStream(new FileOutputStream(file));

			byte[] buf = new byte[1024];
			int len = 0;

			while ((len = bufin.read(buf)) != -1) {
				bufout.write(buf, 0, len);
			}
			bufout.flush();
			return true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} finally {
			if (bufout != null) {
				try {
					bufout.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (bufin != null) {
				try {
					bufin.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
