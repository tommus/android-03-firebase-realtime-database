package pl.itcraft.firebasedatabasesample.ui.item;

import android.view.View;
import android.widget.ImageView;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.mikepenz.fastadapter.FastAdapter;
import com.mikepenz.fastadapter.items.AbstractItem;
import java.util.List;
import pl.itcraft.firebasedatabasesample.R;
import pl.itcraft.firebasedatabasesample.model.Book;
import pl.itcraft.firebasedatabasesample.ui.item.BookItem.ViewHolder;
import pl.itcraft.firebasedatabasesample.utils.glide.GlideApp;

public class BookItem extends AbstractItem<BookItem, ViewHolder> {

	private Book book;

	public BookItem(Book book) {
		this.book = book;
	}

	@Override
	public ViewHolder getViewHolder(View v) {
		return new ViewHolder(v);
	}

	@Override
	public int getType() {
		return R.id.item_books_book;
	}

	@Override
	public int getLayoutRes() {
		return R.layout.item_books_book_cover;
	}

	@Override
	public long getIdentifier() {
		return book.isbn.hashCode();
	}

	class ViewHolder extends FastAdapter.ViewHolder<BookItem> {

		private ImageView image;

		ViewHolder(View itemView) {
			super(itemView);
			image = itemView.findViewById(R.id.img);
		}

		@Override
		public void bindView(BookItem item, List<Object> payloads) {
			GlideApp.with(itemView.getContext())
				.load(item.book.img)
				.placeholder(R.drawable.item_books_book_cover_placeholder)
				.diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
				.into(image);
		}

		@Override
		public void unbindView(BookItem item) {
			image.setImageDrawable(null);
		}
	}
}
