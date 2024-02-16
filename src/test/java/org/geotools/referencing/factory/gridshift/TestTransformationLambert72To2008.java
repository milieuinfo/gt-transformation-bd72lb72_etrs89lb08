package org.geotools.referencing.factory.gridshift;

import java.util.Map;
import org.geotools.api.referencing.FactoryException;
import org.geotools.api.referencing.crs.CoordinateReferenceSystem;
import org.geotools.api.referencing.operation.TransformException;
import org.geotools.referencing.CRS;
import org.geotools.referencing.operation.AbstractCoordinateOperation;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestTransformationLambert72To2008 {
  private static final Logger logger = LoggerFactory.getLogger("");
  private static final String code = "EPSG:8369";
  private Map<String, AbstractCoordinateOperation> grid_trans;

  private static final double[] TEST_POINT_72 = {27114.622720, 199250.636030};
  private static final double[] TEST_POINT_08_CC = {527111.583, 699235.925};
  private static final double TOLERANCE = 3E-3;

  @Before
  public void setUp() throws FactoryException {
    CoordinateReferenceSystem source = CRS.decode("EPSG:31370");
    CoordinateReferenceSystem target = CRS.decode("EPSG:3812");
    grid_trans = CRS.getTransforms(source, target);
  }

  @Test
  public void testTransformationEpsg8369Available() {
    if (grid_trans.containsKey(code)) {
      AbstractCoordinateOperation operation = grid_trans.get(code);
      logger.info(
          "Geotools has successfully detected the transformation "
              + code
              + " "
              + operation.getName()
              + " with accuracy "
              + operation.getAccuracy());
    } else {
      logger.error(
          "Geotools failed to detected the transformation EPSG:8369 "
              + "ProjectedCRS[Belge 1972 / Belgian Lambert 72] ⇨ "
              + "ProjectedCRS[ETRS89 / Belgian Lambert 2008]");
    }
    Assert.assertTrue(grid_trans.containsKey(code));
  }

  @Test
  public void testTransformationAccuracy() throws TransformException {
    logger.info("Testing transformation of coordinates (27114.622720, 199250.636030 )(EPSG:31370) "
            + "to be close to (527111.583, 699235.925) (EPSG:3812)."
        );
    double[] p = new double[2];
    AbstractCoordinateOperation operation = grid_trans.get(code);
    operation.getMathTransform().transform(TEST_POINT_72, 0, p, 0, 1);
    Assert.assertEquals("x-coordinate must not deviate more than 3mm from cConvert result.", p[0], TEST_POINT_08_CC[0], TOLERANCE);
    Assert.assertEquals("y-coordinate must not deviate more than 3mm from cConvert result.", p[1], TEST_POINT_08_CC[1], TOLERANCE);
  }
}
