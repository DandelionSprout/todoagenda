package org.andstatus.todoagenda;

import org.andstatus.todoagenda.provider.QueryResultsStorage;
import org.andstatus.todoagenda.util.DateUtil;
import org.andstatus.todoagenda.widget.DayHeader;
import org.json.JSONException;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

/**
 * @author yvolk@yurivolkov.com
 */
public class DayHeadersShiftTest extends BaseWidgetTest {

    @Test
    public void testDayHeadersShift() throws IOException, JSONException {
        final String method = "testDayHeadersShift";
        QueryResultsStorage inputs = provider.loadResultsAndSettings(
                org.andstatus.todoagenda.tests.R.raw.day_headers_shift);
        provider.addResults(inputs.getResults());

        playResults(method);
        DayHeader dayHeader0 = (DayHeader) factory.getWidgetEntries().get(0);


        assertEquals("First day header should be Jan 8\n" + factory.getWidgetEntries(), 8,
                dayHeader0.entryDate.dayOfMonth().get());
        String dayHeaderTitle = DateUtil.createDayHeaderTitle(getSettings(), dayHeader0.entryDate);
        assertEquals("First day header should show Jan 8\n" + factory.getWidgetEntries() + "\n",
                "Today, January 8", dayHeaderTitle);
    }
}
