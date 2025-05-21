package shoppingcart.service.interfaces;

public interface AuthenResultHandler {
	public void handleSuccessfulLogin(String id);
	public void handleFailLogin(String id);
}
