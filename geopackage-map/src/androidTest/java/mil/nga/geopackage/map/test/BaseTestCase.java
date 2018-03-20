package mil.nga.geopackage.map.test;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.StrictMode;
import android.test.ActivityInstrumentationTestCase2;

import mil.nga.geopackage.db.metadata.GeoPackageMetadataDb;
import mil.nga.geopackage.map.GeoPackageMapActivity;

/**
 * Abstract Base Test Case
 *
 * @author osbornb
 */
public abstract class BaseTestCase extends
        ActivityInstrumentationTestCase2<GeoPackageMapActivity> {

    /**
     * Activity
     */
    protected Activity activity = null;

    /**
     * Test context
     */
    protected Context testContext = null;

    /**
     * Constructor
     */
    public BaseTestCase() {
        super(GeoPackageMapActivity.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();

        // Set the activity and test context
        activity = getActivity();
        testContext = TestUtils.getTestContext(activity);

        if (BuildConfig.DEBUG && Build.VERSION.SDK_INT >= 11) {
            StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                    .detectLeakedSqlLiteObjects()
                    .detectLeakedClosableObjects()
                    .penaltyLog()
                    .penaltyDeath()
                    .build());
        }

        activity.deleteDatabase(GeoPackageMetadataDb.DATABASE_NAME);
    }

}
