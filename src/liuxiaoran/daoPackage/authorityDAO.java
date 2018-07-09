package liuxiaoran.daoPackage;

import java.util.List;

import liuxiaoran.JavaBean.authority;

public interface authorityDAO extends DAO<authority> {
         String getAuth_name(Integer user_id);
         List<authority> getManagers();
         void becomeManager(Integer user_id);
         void addAuthority(Integer user_id);
}
