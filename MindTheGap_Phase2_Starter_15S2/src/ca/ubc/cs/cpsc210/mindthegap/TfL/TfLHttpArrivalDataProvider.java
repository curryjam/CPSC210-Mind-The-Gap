package ca.ubc.cs.cpsc210.mindthegap.TfL;

import android.util.Log;
import ca.ubc.cs.cpsc210.mindthegap.model.Line;
import ca.ubc.cs.cpsc210.mindthegap.model.Station;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Wrapper for TfL Arrival Data Provider
 */
public class TfLHttpArrivalDataProvider extends AbstractHttpDataProvider {
    private Station stn;

    public TfLHttpArrivalDataProvider(Station stn) {
        super();
        this.stn = stn;
    }

    @Override
    /**
     * Produces URL used to query TfL web service for expected arrivals at
     * station specified in call to constructor.
     *
     * @returns URL to query TfL web service for arrival data
     */
    protected URL getURL() throws MalformedURLException {
        String request = "";

        String urlLineName = "";
        Boolean lineName = false;

        for (Line next : stn.getLines()) {
            String lineID = next.getId();
            if (lineName) {
                urlLineName = urlLineName + "," + lineID;
            } else {
                urlLineName = lineID;
                lineName = true;
            }
        }

        String stnID = stn.getID();

        request = "https://api.tfl.gov.uk/Line/" + urlLineName + "/Arrivals?stopPointId=" + stnID + "&app_id=&app_key=";

        Log.e("DataProvider", request);

        return new URL(request);
    }
}

