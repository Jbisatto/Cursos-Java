package bancoDeDados;

public interface IHelperDAL<T> {
	void insert(T item);
	void delete(T item);

}
