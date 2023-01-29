package communication;

import java.io.Serializable;

public enum Operation  implements Serializable{
    // Auth
    LOGIN,
    
    // Products
    GET_ALL_PRODUCTS,
    
    // Clients
    GET_ALL_CLIENTS,
    LOAD_CLIENT,
    SAVE_CLIENT,
    DELETE_CLIENT,
    SEARCH_CLIENTS,
    
    // Brokers
    GET_ALL_BROKERS,
    LOAD_BROKER,
    SAVE_BROKER,
    DELETE_BROKER,
    SEARCH_BROKERS,
    
    // Deals
    SAVE_DEAL,
    GET_ALL_DEALS,
    SEARCH_DEALS,
}
