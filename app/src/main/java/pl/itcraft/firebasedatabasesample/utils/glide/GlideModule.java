package pl.itcraft.firebasedatabasesample.utils.glide;

import android.content.Context;
import android.util.Log;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.module.AppGlideModule;

@com.bumptech.glide.annotation.GlideModule
public class GlideModule extends AppGlideModule {

	@Override
	public void applyOptions(Context context, GlideBuilder builder) {
		super.applyOptions(context, builder);
		builder.setLogLevel(Log.VERBOSE);
	}

	@Override
	public boolean isManifestParsingEnabled() {
		return false;
	}
}
