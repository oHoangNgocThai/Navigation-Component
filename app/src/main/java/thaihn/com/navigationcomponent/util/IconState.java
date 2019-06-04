package thaihn.com.navigationcomponent.util;

import androidx.annotation.StringDef;

@StringDef({
        Constants.CLEAR_DAY, Constants.CLEAR_NIGHT, Constants.RAIN, Constants.SNOW,
        Constants.CLOUDY, Constants.FOG, Constants.SLEET, Constants.WIND,
        Constants.PARTLY_CLOUDY_DAY, Constants.PARTLY_CLOUDY_NIGHT
})
public @interface IconState {
}
