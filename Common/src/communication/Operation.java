package communication;

import java.io.Serializable;

public enum Operation  implements Serializable{
    // Auth
    LOGIN,
    
    // Products
    GET_ALL_PRODUCTS,
    SAVE_PRODUCT,
    DELETE_PRODUCT,
    
    // Clients
    GET_ALL_CLIENTS,
    SAVE_CLIENT,
    DELETE_CLIENT,
    SEARCH_CLIENTS,
    
    // Brokers
    GET_ALL_BROKERS,
    SAVE_BROKER,
    DELETE_BROKER,
    SEARCH_BROKERS,
    
    // Deals
    SAVE_DEAL,
    GET_ALL_DEALS,
    SEARCH_DEALS,
}
