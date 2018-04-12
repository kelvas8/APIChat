package chat.server.handlers;

public interface IHandler extends Runnable {

    void setMessages();
    void setDataContacts();
    void setData();
    void sentData(Object obj);

}
