import org.sql2o.*;
import java.util.List;

public class Client{
  private int id;
  private int barberid;
  private String name;

  public Client (String name, int barberid){
    this.name = name;
    this.barberid = barberid;
  }

  public String getName(){
    return name;
  }

  public int getId(){
    return id;
  }

  public int getBarberId(){
    return barberid;
  }

  @Override
  public boolean equals(Object otherClient){
    if(!(otherClient instanceof Client)){
      return false;
    } else {
      Client newClient = (Client) otherClient;
      return this.getId() == newClient.getId() &&
      this.getName().equals(newClient.getName()) &&
      this.getBarberId() == newClient.getBarberId();
    }
  }

  public void save(){
    try(Connection con = DB.sql2o.open()){
      String sql = "INSERT INTO clients (name, barberid) VALUES (:name, :barberid);";
      this.id = (int) con.createQuery(sql, true)
      .addParameter("name", this.name)
      .addParameter("barberid", this.barberid)
      .executeUpdate()
      .getKey();
    }
  }

  public static List<Client> all(){
    try(Connection con = DB.sql2o.open()){
      String sql = "SELECT * FROM clients;";
      return con.createQuery(sql).executeAndFetch(Client.class);
    }
  }

  public static Client find(int id){
    try(Connection con = DB.sql2o.open()){
      String sql = "SELECT * FROM clients where id=:id;";
      Client client = con.createQuery(sql)
      .addParameter("id", id)
      .executeAndFetchFirst(Client.class);
    return client;
    }
  }

  public void updateClientName(String newName){
    try(Connection con = DB.sql2o.open()){
      String sql = "UPDATE clients SET name = :name WHERE id=:id;";
      con.createQuery(sql)
      .addParameter("name", newName)
      .addParameter("id", id)
      .executeUpdate();
    }
  }

  public void delete(){
    try(Connection con = DB.sql2o.open()){
      String sql = "DELETE FROM clients WHERE id=:id;";
      con.createQuery(sql)
      .addParameter("id", id)
      .executeUpdate();
    }
  }

}
