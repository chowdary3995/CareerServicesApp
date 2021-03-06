/**
 * 
 */
package ia.umkc.career;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.util.Log;

/**
 * @author rr5h4
 * 
 */

public class PresentationOne extends ContentProvider {
	public final static Uri CONTENT_URI = Uri
			.parse("content://ia.umkc.career/");
	private static final HashMap<String, String> MIME_TYPES = new HashMap<String, String>();

	static {
		MIME_TYPES.put(".pdf", "application/pdf");
	}

	private String fileName;

	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * @param fileName
	 *            the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@Override
	public boolean onCreate() {
		File f = new File(getContext().getFilesDir(), "DRESS_TO_IMPRESS.pdf");
		if (!f.exists()) {
			AssetManager assets = getContext().getResources().getAssets();

			try {
				copy(assets.open("DRESS_TO_IMPRESS.pdf"), f);
			} catch (IOException e) {
				Log.e("FileProvider", "Exception copying from assets", e);

				return (false);
			}
		}

		return (true);
	}

	@Override
	public String getType(Uri uri) {
		String path = uri.toString();

		for (String extension : MIME_TYPES.keySet()) {
			if (path.endsWith(extension)) {
				return (MIME_TYPES.get(extension));
			}
		}

		return (null);
	}

	@Override
	public ParcelFileDescriptor openFile(Uri uri, String mode)
			throws FileNotFoundException {
		File f = new File(getContext().getFilesDir(), uri.getPath());

		if (f.exists()) {
			return (ParcelFileDescriptor.open(f,
					ParcelFileDescriptor.MODE_READ_ONLY));
		}

		throw new FileNotFoundException(uri.getPath());
	}

	@Override
	public Cursor query(Uri url, String[] projection, String selection,
			String[] selectionArgs, String sort) {
		throw new RuntimeException("Operation not supported");
	}

	@Override
	public Uri insert(Uri uri, ContentValues initialValues) {
		throw new RuntimeException("Operation not supported");
	}

	@Override
	public int update(Uri uri, ContentValues values, String where,
			String[] whereArgs) {
		throw new RuntimeException("Operation not supported");
	}

	@Override
	public int delete(Uri uri, String where, String[] whereArgs) {
		throw new RuntimeException("Operation not supported");
	}

	static private void copy(InputStream in, File dst) throws IOException {
		FileOutputStream out = new FileOutputStream(dst);
		byte[] buf = new byte[1024];
		int len;

		while ((len = in.read(buf)) > 0) {
			out.write(buf, 0, len);
		}

		in.close();
		out.close();
	}
}