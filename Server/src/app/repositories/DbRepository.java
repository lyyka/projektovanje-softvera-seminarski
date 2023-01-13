/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.repositories;

import app.database.DatabaseBroker;

public interface DbRepository<T>  extends IRepository<T>{
    default public void connect() throws Exception{
        DatabaseBroker.getInstance().getConnection();
    }
    
    default public void disconnect() throws Exception{
        DatabaseBroker.getInstance().getConnection().close();
    }
    
    default public void commit() throws Exception{
        DatabaseBroker.getInstance().getConnection().commit();
    }
    
    default public void rollback() throws Exception{
        DatabaseBroker.getInstance().getConnection().rollback();
    }
}
