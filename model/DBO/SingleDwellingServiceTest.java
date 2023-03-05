package model.DBO;

import model.Apartment;
import model.Condo;
import model.House;
import model.SingleDwelling;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;


public class SingleDwellingServiceTest {

    @Mock private SingleDwellingDao singleDwellingDaoDao;

    @Rule public MockitoRule rule = MockitoJUnit.rule();

    @Test
    public void testFindById() {
        MockitoAnnotations.initMocks(this);
        SingleDwellingService singleDwellingService = new SingleDwellingService(singleDwellingDaoDao);
        singleDwellingService.findById(1L);
        Mockito.verify(singleDwellingDaoDao).findById(1L);
    }

    @Test
    public void testHouse() {
        SingleDwellingService singleDwellingService = new SingleDwellingService(singleDwellingDaoDao);
        Mockito.when(singleDwellingDaoDao.findById(1L)).thenReturn(createTestHouse());
        SingleDwelling actual = singleDwellingService.findById(1L);
        Assert.assertEquals("St Catherine E", actual.getSreet());
        Assert.assertEquals("Montreal", actual.getCity());
        Assert.assertEquals("H3H 1B4", actual.getPostalCode());
        Mockito.verify(singleDwellingDaoDao).findById(1L);
    }

    private SingleDwelling createTestHouse() {
        SingleDwelling sd = new House();
        sd.setStreetNumber(1001);
        sd.setStreet("St Catherine E");
        sd.setCity("Montreal");
        sd.setBathrooms(2);
        sd.setBedrooms(3);
        sd.setPostalCode("H3H 1B4");

        return sd;
    }
    @Test
    public void testCondo() {
        SingleDwellingService singleDwellingService = new SingleDwellingService(singleDwellingDaoDao);
        Mockito.when(singleDwellingDaoDao.findById(1L)).thenReturn(createTestCondo());
        SingleDwelling actual = singleDwellingService.findById(1L);
        Assert.assertEquals("St Catherine E", actual.getStreet());
        Assert.assertEquals("Montreal", actual.getCity());
        Assert.assertEquals("H3H 1B4", actual.getPostalCode());
        Mockito.verify(singleDwellingDaoDao).findById(1L);
    }
    private SingleDwelling createTestCondo() {
        SingleDwelling sd = new Apartment();
        sd.setStreetNumber(1001);
        sd.setStreet("St Catherine E");
        sd.setCity("Montreal");
        sd.setBathrooms(2);
        sd.setBedrooms(3);
        sd.setPostalCode("H3H 1B4");

        return sd;
    }
    @Test
    public void testApt() {
        SingleDwellingService singleDwellingService = new SingleDwellingService(singleDwellingDaoDao);
        Mockito.when(singleDwellingDaoDao.findById(1L)).thenReturn(createTestApt());
        SingleDwelling actual = singleDwellingService.findById(1L);
        Assert.assertEquals("St Catherine E", actual.getStreet());
        Assert.assertEquals("Montreal", actual.getCity());
        Assert.assertEquals("H3H 1B4", actual.getPostalCode());
        Mockito.verify(singleDwellingDaoDao).findById(1L);
    }
    private SingleDwelling createTestApt() {
        SingleDwelling sd = new Condo();
        sd.setStreetNumber(1001);
        sd.setStreet("St Catherine E");
        sd.setCity("Montreal");
        sd.setBathrooms(2);
        sd.setBedrooms(3);
        sd.setPostalCode("H3H 1B4");

        return sd;
    }
}
