import java.util.Scanner;
// A factory that creates an 'SafeCreateOperation' object.
//
public class SafeCreateFactory implements SafeFactory // TODO: activate clause.
{
    @Override
    public SafeOperation create(Scanner sc) throws FactoryException {
        try{
            return new SafeCreateOperation(sc.nextInt(), sc.nextInt());
        }catch (Exception e){
            throw new FactoryException();
        }

    }

    // TODO: define missing parts of this class.
}
