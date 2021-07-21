package truecaller;

import truecaller.tries.ContactTrie;
import lombok.Getter;

public class GlobalContacts {
    private GlobalContacts() {
    }
    private static volatile GlobalContacts INSTANCE = null;

    public static GlobalContacts getInstance(){
        if(INSTANCE == null){
            synchronized (GlobalContacts.class){
                if(INSTANCE == null){
                    INSTANCE = new GlobalContacts();
                }
            }
        }
        return INSTANCE;
    }
    @Getter
    private ContactTrie contactTrie = new ContactTrie();
}
