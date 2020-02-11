package pobj.expr;

public interface Expression {
   public<T> T accept(IVisitor<T> visitor);
   public int eval();
}
