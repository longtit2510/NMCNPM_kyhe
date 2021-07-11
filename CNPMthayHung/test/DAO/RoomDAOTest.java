/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Room;
import java.sql.Connection;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Snowyy
 */
public class RoomDAOTest {

    public RoomDAOTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testSearchRoomException1() {
        System.out.println("searchRoomException1");
        String key = "xxxxxxx";
        RoomDAO instance = new RoomDAO();
        ArrayList<Room> result = instance.searchRoom(key);
        assertNotNull(result);
        assertEquals(0, result.size());
    }

    @Test
    public void testSearchRoomException2() {
        System.out.println("searchRoomException2");
        String key = "phong ba";
        RoomDAO instance = new RoomDAO();
        ArrayList<Room> result = instance.searchRoom(key);
        assertEquals(0, result.size());
    }

    @Test
    public void testSearchRoomStandard1() {
        String key = "phong mot";
        RoomDAO instance = new RoomDAO();
        ArrayList<Room> listRoom = instance.searchRoom(key);
        assertNotNull(listRoom);
        assertEquals(2, listRoom.size());
        for (int i = 0; i < listRoom.size(); i++) {
            assertTrue(listRoom.get(i).getName().toLowerCase().
                    contains(key.toLowerCase()));
        }
        return;
    }

    public void testSearchRoomStandard2() {
        String key = "phong doi";
        RoomDAO instance = new RoomDAO();
        ArrayList<Room> listRoom = instance.searchRoom(key);
        assertNotNull(listRoom);
        assertEquals(2, listRoom.size());
        for (int i = 0; i < listRoom.size(); i++) {
            assertTrue(listRoom.get(i).getName().toLowerCase().
                    contains(key.toLowerCase()));
        }
        return;
    }

    @Test
    public void testUpdateRoom() {
        Connection con = DAO.con;
        String newType = "test type";
        String newDes = "test des";
        float newPrice = 5f;
        String key = "phong mot";
        RoomDAO rd = new RoomDAO();
        try {
            con.setAutoCommit(false);
            ArrayList<Room> lr = rd.searchRoom(key);
            lr.get(0).setType(newType);
            lr.get(0).setDes(newDes);
            lr.get(0).setPrice(newPrice);
            rd.updateRoom(lr.get(0));
//test the new updated row
            lr.clear();
            lr = rd.searchRoom(key);
            assertTrue(lr.get(0).getName().contains(key));
            assertEquals(newType, lr.get(0).getType());
            assertEquals(newPrice, lr.get(0).getPrice(), 0.000001f);
            assertEquals(newDes, lr.get(0).getDes());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                con.rollback();
                con.setAutoCommit(true);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return;
    }
}

