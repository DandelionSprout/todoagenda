package org.andstatus.todoagenda;

import org.andstatus.todoagenda.prefs.InstanceSettings;
import org.andstatus.todoagenda.provider.MockCalendarContentProvider;
import org.andstatus.todoagenda.widget.LastEntry;
import org.joda.time.DateTime;
import org.junit.After;
import org.junit.Before;

import static org.andstatus.todoagenda.RemoteViewsFactory.MIN_MILLIS_BETWEEN_RELOADS;
import static org.junit.Assert.assertTrue;

/**
 * @author yvolk@yurivolkov.com
 */
public class BaseWidgetTest {
    final static String TAG = BaseWidgetTest.class.getSimpleName();

    protected MockCalendarContentProvider provider = null;
    protected RemoteViewsFactory factory = null;

    @Before
    public void setUp() throws Exception {
        provider = MockCalendarContentProvider.getContentProvider();
        factory = new RemoteViewsFactory(provider.getContext(), provider.getWidgetId());
        assertTrue(factory.getWidgetEntries().get(0) instanceof LastEntry);
    }

    @After
    public void tearDown() throws Exception {
        MockCalendarContentProvider.tearDown();
    }

    DateTime dateTime(
            int year,
            int monthOfYear,
            int dayOfMonth) {
        return dateTime(year, monthOfYear, dayOfMonth, 0, 0);
    }

    DateTime dateTime(
            int year,
            int monthOfYear,
            int dayOfMonth,
            int hourOfDay,
            int minuteOfHour) {
        return new DateTime(year, monthOfYear, dayOfMonth, hourOfDay, minuteOfHour, 0, 0,
                provider.getSettings().clock().getZone());
    }

    protected void playResults(String tag) {
        provider.updateAppSettings(tag);

        EnvironmentChangedReceiver.updateWidget(provider.getContext(), provider.getWidgetId());

        factory.onDataSetChanged();
        factory.logWidgetEntries(tag);

        EnvironmentChangedReceiver.sleep(MIN_MILLIS_BETWEEN_RELOADS);
    }

    protected InstanceSettings getSettings() {
        return provider.getSettings();
    }
}
