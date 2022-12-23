package communication;

import java.io.Serializable;

public enum Operation  implements Serializable{
    // Auth
    LOGIN,
    
    // Products
    GET_ALL_PRODUCTS,
    
    // Clients
    GET_ALL_CLIENTS,
    SAVE_CLIENT,
    DELETE_CLIENT,
    
    // Brokers
    GET_ALL_BROKERS,
    SAVE_BROKER,
    DELETE_BROKER,
    
    // Deals
    // ...
}
