import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;

public class ClientTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void barber_instantiatesCorrectly_true(){
    Client testClient = new Client("red", 1);
    assertTrue(testClient instanceof Client);
  }

  @Test
  public void getName_returnsClientName_red(){
    Client testClient = new Client("red", 1);
    assertEquals("red", testClient.getName());
  }

  @Test
  public void save_createsIdForClientInDatabase_true(){
    Client testClient = new Client("red", 1);
    testClient.save();
    assertTrue(testClient.getId() > 0);
  }

  @Test
  public void all_RetrievesAllInstancesOfClient_true(){
    Client testClient = new Client("red", 1);
    testClient.save();
    Client testClient2 = new Client("yellow", 2);
    testClient2.save();
    assertEquals(true, Client.all().get(0).equals(testClient));
    assertEquals(true, Client.all().get(1).equals(testClient2));
  }

  @Test
  public void find_returnsClientWithTheSameId_true(){
    Client testClient = new Client("red", 1);
    testClient.save();
    Client testClient2 = new Client("yellow", 2);
    testClient2.save();
    assertEquals(Client.find(testClient2.getId()), testClient2);
  }

  @Test
  public void updateClientName_updatesClientName_yellow(){
    Client testClient = new Client("red", 1);
    testClient.save();
    testClient.updateClientName("yellow");
    assertEquals("yellow", Client.find(testClient.getId()).getName());
  }

  @Test public void delete_deletesClientFromDatabase_true(){
    Client testClient = new Client("red", 1);
    testClient.save();
    int testClientId = testClient.getId();
    testClient.delete();
    assertEquals(null, Client.find(testClient.getId()));
  }
}
