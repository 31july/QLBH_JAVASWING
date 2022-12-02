package dao;

import java.util.List;

public abstract class DAO_Main <EntityType, KeyType> {
	  public abstract void insert(EntityType paramEntityType);
	  
	  public abstract void update(EntityType paramEntityType);
	  
	  public abstract void delete(KeyType paramKeyType);
	  
	  public abstract EntityType selectById(KeyType paramKeyType);
	  
	  public abstract List<EntityType> selectAll();
	  
	  protected abstract List<EntityType> selectBySql(String paramString, Object... paramVarArgs);
}
