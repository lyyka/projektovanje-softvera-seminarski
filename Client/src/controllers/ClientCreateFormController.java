package controllers;

import forms.ClientCreateForm;
import forms.MainForm;

public class ClientCreateFormController {

    private MainForm mainForm;
    private ClientCreateForm clientCreateForm;
    
    public ClientCreateFormController() {
        this.mainForm = new MainForm();
        this.clientCreateForm = new ClientCreateForm(mainForm, true);
    }
    
    
    public void createClient()
    {
        
    }
}
