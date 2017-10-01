package ridickle.co.kr.mylittlepet.main;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ListView;

import com.nhn.android.maps.NMapOverlayItem;
import com.nhn.android.maps.overlay.NMapPOIitem;
import com.nhn.android.mapviewer.overlay.NMapResourceProvider;

import ridickle.co.kr.mylittlepet.R;

/**
 * Created by ridickle on 2017. 6. 12..
 */

public class NMapViewerResourceProvider extends NMapResourceProvider {

    private static final String LOG_TAG = "ResourceProvider";
    private static final boolean DEBUG = false;

    private static final Bitmap.Config BITMAP_CONFIG_DEFAULT = Bitmap.Config.ARGB_8888;
    private final Rect mTempRect = new Rect();
    private final Paint mTextPaint = new Paint();

    private static final Typeface POI_FONT_TYPEFACE = null;//Typeface.DEFAULT_BOLD;
    private static final float POI_FONT_SIZE_NUMBER = 10.0F;

    private static final int TEXT_COLOR_NORMAL = 0xFFFFFFFF;
    private static final int TEXT_COLOR_PRESSED = 0xFF9CA1AA;
    private static final int TEXT_COLOR_SELECTED = 0xFFFFFFFF;
    private static final int TEXT_COLOR_FOCUSED = 0xFFFFFFFF;

    public NMapViewerResourceProvider(Context context) {
        super(context);
    }

    @Override
    protected int findResourceIdForMarker(int markerId, boolean focused) {      // 리소스 아이디로 마커 찾기
        int resourceId = 0;

        if (DEBUG) {
            Log.i(LOG_TAG, "getResourceIdForMarker: markerId=" + markerId + ", focused=" + focused);
        }

        if (markerId < NMapPOIflagType.SINGLE_MARKER_END) {
            resourceId = getResourceIdOnMapView(markerId, focused, mResourceIdsForMarkerOnMap);
            if (resourceId > 0) {
                return resourceId;
            }
        }

        if (markerId >= NMapPOIflagType.NUMBER_BASE && markerId < NMapPOIflagType.NUMBER_END) { // Direction Number icons

        } else if (markerId >= NMapPOIflagType.CUSTOM_BASE && markerId < NMapPOIflagType.CUSTOM_END) { // Custom POI icons

        }

        return resourceId;
    }

    @Override
    protected Drawable getDrawableForMarker(int markerId, boolean focused, NMapOverlayItem item) {      // 마커 그리기
        Drawable drawable = null;

        if (markerId >= NMapPOIflagType.NUMBER_BASE && markerId < NMapPOIflagType.NUMBER_END) { // Direction Number icons
            int resourceId = (focused) ? R.mipmap.ic_map_no_02 : R.mipmap.ic_map_no_01;
            int fontColor = (focused) ? R.color.POI_FONT_COLOR_ALPHABET : R.color.POI_FONT_COLOR_NUMBER;

            String strNumber = String.valueOf(markerId - NMapPOIflagType.NUMBER_BASE);

            drawable = getDrawableWithNumber(resourceId, strNumber, 0.0F, fontColor, POI_FONT_SIZE_NUMBER);
        } else if (markerId >= NMapPOIflagType.CUSTOM_BASE && markerId < NMapPOIflagType.CUSTOM_END) { // Custom POI icons

        }

        return drawable;
    }

    @Override
    public Drawable getCalloutBackground(NMapOverlayItem item) {            //말풍선 배경 이미지를 반환한다.
        if (item instanceof NMapPOIitem) {
            NMapPOIitem poiItem = (NMapPOIitem)item;

            if (poiItem.showRightButton()) {
                Drawable drawable = mContext.getResources().getDrawable(R.mipmap.bg_speech);
                return drawable;
            }
        }

        Drawable drawable = mContext.getResources().getDrawable(R.mipmap.pin_ballon_bg);

        return drawable;
    }

    @Override
    public String getCalloutRightButtonText(NMapOverlayItem item) {         // 말풍선 오른쪽 버튼의 타이틀을 반환한다.
        if (item instanceof NMapPOIitem) {
            NMapPOIitem poiItem = (NMapPOIitem)item;

            if (poiItem.showRightButton()) {
                return mContext.getResources().getString(R.string.str_done);
            }
        }

        return null;
    }

    @Override
    public Drawable[] getCalloutRightButton(NMapOverlayItem item) {         // 말풍선 오른쪽 버튼의 배경 이미지를 반환한다.
        if (item instanceof NMapPOIitem) {
            NMapPOIitem poiItem = (NMapPOIitem)item;

            if (poiItem.showRightButton()) {
                Drawable[] drawable = new Drawable[3];

                drawable[0] = mContext.getResources().getDrawable(R.mipmap.btn_green_normal);
                drawable[1] = mContext.getResources().getDrawable(R.mipmap.btn_green_pressed);
                drawable[2] = mContext.getResources().getDrawable(R.mipmap.btn_green_highlight);

                return drawable;
            }
        }

        return null;
    }

    @Override
    public Drawable[] getCalloutRightAccessory(NMapOverlayItem item) {      // 말풍선 오른쪽 아이콘 이미지를 반환한다.
        if (item instanceof NMapPOIitem) {
            NMapPOIitem poiItem = (NMapPOIitem)item;

            if (poiItem.hasRightAccessory() && (poiItem.getRightAccessoryId() > 0)) {
                Drawable[] drawable = new Drawable[3];

                switch (poiItem.getRightAccessoryId()) {
                    case NMapPOIflagType.CLICKABLE_ARROW:
                        drawable[0] = mContext.getResources().getDrawable(R.mipmap.pin_ballon_arrow);
                        drawable[1] = mContext.getResources().getDrawable(R.mipmap.pin_ballon_on_arrow);
                        drawable[2] = mContext.getResources().getDrawable(R.mipmap.pin_ballon_on_arrow);
                        break;
                }

                return drawable;
            }
        }

        return null;
    }

    /**
     * 말풍선의 텍스트 색상을 customize한다.
     *
     * @param item
     * @return
     * @see com.nhn.android.mapviewer.overlay.NMapCalloutCustomOverlay.ResourceProvider#getCalloutTextColors(com.nhn.android.maps.NMapOverlayItem)
     */
    @Override
    public int[] getCalloutTextColors(NMapOverlayItem item) {
        int[] colors = new int[4];
        colors[0] = TEXT_COLOR_NORMAL;
        colors[1] = TEXT_COLOR_PRESSED;
        colors[2] = TEXT_COLOR_SELECTED;
        colors[3] = TEXT_COLOR_FOCUSED;
        return colors;
    }

    @Override
    public int getParentLayoutIdForOverlappedListView() {
        // not supported
        return 0;
    }

    @Override
    public int getOverlappedListViewId() {
        // not supported
        return 0;
    }

    @Override
    public int getLayoutIdForOverlappedListView() {
        // not supported
        return 0;
    }

    @Override
    public void setOverlappedListViewLayout(ListView listView, int i, int i1, int i2) {

    }

    @Override
    public int getListItemLayoutIdForOverlappedListView() {
        // not supported
        return 0;
    }

    @Override
    public int getListItemTextViewId() {
        // not supported
        return 0;
    }

    @Override
    public int getListItemTailTextViewId() {
        // not supported
        return 0;
    }

    @Override
    public int getListItemImageViewId() {
        // not supported
        return 0;
    }

    @Override
    public int getListItemDividerId() {
        // not supported
        return 0;
    }

    @Override
    public void setOverlappedItemResource(NMapPOIitem nMapPOIitem, ImageView imageView) {

    }

    @Override
    public Drawable[] getLocationDot() {        // 현재 위치 표시를 위한 이미지를 반환한다.
        Drawable[] drawable = new Drawable[2];

        drawable[0] = mContext.getResources().getDrawable(R.mipmap.pubtrans_ic_mylocation_off);
        drawable[1] = mContext.getResources().getDrawable(R.mipmap.pubtrans_ic_mylocation_on);

        for (int i = 0; i < drawable.length; i++) {
            int w = drawable[i].getIntrinsicWidth() / 2;
            int h = drawable[i].getIntrinsicHeight() / 2;

            drawable[i].setBounds(-w, -h, w, h);
        }

        return drawable;
    }



    @Override
    public Drawable getDirectionArrow() {       // 나침반 각도 표시를 위한 이미지를 반환한다.

        Drawable drawable = mContext.getResources().getDrawable(R.mipmap.ic_angle);

        if (drawable != null) {
            int w = drawable.getIntrinsicWidth() / 2;
            int h = drawable.getIntrinsicHeight() / 2;

            drawable.setBounds(-w, -h, w, h);
        }

        return drawable;
    }

    /**
     * Class to find resource Ids on map view
     */
    private class ResourceIdsOnMap {

        int markerId;
        int resourceId;
        int resourceIdFocused;

        ResourceIdsOnMap(int markerId, int resourceId, int resourceIdFocused) {
            this.markerId = markerId;
            this.resourceId = resourceId;
            this.resourceIdFocused = resourceIdFocused;
        }
    }

    // Resource Ids for single icons
    private final ResourceIdsOnMap mResourceIdsForMarkerOnMap[] = {
            // Spot, Pin icons
            new ResourceIdsOnMap(NMapPOIflagType.PIN, R.mipmap.ic_pin_01, R.mipmap.ic_pin_02),
            new ResourceIdsOnMap(NMapPOIflagType.SPOT, R.mipmap.ic_pin_01, R.mipmap.ic_pin_02),

            // Direction POI icons: From, To
            new ResourceIdsOnMap(NMapPOIflagType.FROM, R.mipmap.ic_map_start, R.mipmap.ic_map_start_over),
            new ResourceIdsOnMap(NMapPOIflagType.TO, R.mipmap.ic_map_arrive, R.mipmap.ic_map_arrive_over),
    };

    private int getResourceIdOnMapView(int markerId, boolean focused, ResourceIdsOnMap resourceIdsArray[]) {
        int resourceId = 0;

        for (ResourceIdsOnMap resourceIds : resourceIdsArray) {
            if (resourceIds.markerId == markerId) {
                resourceId = (focused) ? resourceIds.resourceIdFocused : resourceIds.resourceId;
                break;
            }
        }

        return resourceId;
    }

    public Drawable getDrawableWithNumber(int resourceId, String strNumber, float offsetY, int fontColor, float fontSize) {

        Bitmap textBitmap = getBitmapWithText(resourceId, strNumber, fontColor, fontSize, offsetY);

        //Log.i(LOG_TAG, "getDrawableWithNumber: width=" + textBitmap.getWidth() + ", height=" + textBitmap.getHeight() + ", density=" + textBitmap.getDensity());

        // set bounds
        Drawable marker = new BitmapDrawable(mContext.getResources(), textBitmap);
        if (marker != null) {
            NMapOverlayItem.boundCenter(marker);
        }

        //Log.i(LOG_TAG, "getDrawableWithNumber: width=" + marker.getIntrinsicWidth() + ", height=" + marker.getIntrinsicHeight());

        return marker;
    }

    private Bitmap getBitmapWithText(int resourceId, String strNumber, int fontColor, float fontSize, float offsetY) {
        Bitmap bitmapBackground = BitmapFactory.decodeResource(mContext.getResources(), resourceId);

        int width = bitmapBackground.getWidth();
        int height = bitmapBackground.getHeight();
        //Log.i(LOG_TAG, "getBitmapWithText: width=" + width + ", height=" + height + ", density=" + bitmapBackground.getDensity());

        Bitmap textBitmap = Bitmap.createBitmap(width, height, BITMAP_CONFIG_DEFAULT);

        Canvas canvas = new Canvas(textBitmap);

        canvas.drawBitmap(bitmapBackground, 0, 0, null);

        // set font style
        mTextPaint.setColor(fontColor);
        // set font size
        mTextPaint.setTextSize(fontSize * mScaleFactor);
        // set font type
        if (POI_FONT_TYPEFACE != null) {
            mTextPaint.setTypeface(POI_FONT_TYPEFACE);
        }

        // get text offset
        mTextPaint.getTextBounds(strNumber, 0, strNumber.length(), mTempRect);
        float offsetX = (width - mTempRect.width()) / 2 - mTempRect.left;
        if (offsetY == 0.0F) {
            offsetY = (height - mTempRect.height()) / 2 + mTempRect.height();
        } else {
            offsetY = offsetY * mScaleFactor + mTempRect.height();
        }

        //Log.i(LOG_TAG, "getBitmapWithText: number=" + number + ", focused=" + focused);
        //Log.i(LOG_TAG, "getBitmapWithText: offsetX=" + offsetX + ", offsetY=" + offsetY + ", boundsWidth=" + mTempRect.width() + ", boundsHeight=" + mTempRect.height());

        // draw text
        canvas.drawText(strNumber, offsetX, offsetY, mTextPaint);

        return textBitmap;
    }
}
