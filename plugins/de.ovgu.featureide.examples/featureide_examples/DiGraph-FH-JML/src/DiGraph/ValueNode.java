


















public abstract  class  ValueNode  implements NodeType, Cloneable {
	

    protected  /*@spec_public@*/ /*@nullable@*/  Object value;

	/*@ 

     also @*/ /*@ 
       public normal_behavior
         requires o instanceof ValueNode;
         ensures \result
             ==> JMLNullSafe.equals(value, ((ValueNode)o).value);
     also
       public normal_behavior
         requires !(o instanceof ValueNode);
         ensures \result == false; @*/
	
      
    public boolean equals( /*@nullable@*/ Object o) {
        if (!(o instanceof ValueNode)) { 
            return false;
        } else if (value == null) {
            return ((ValueNode) o).getValue() == null;
        } else {
            return value.equals(((ValueNode) o).getValue());
        }
    }

	/*@ 

     also @*/ /*@ 
       public normal_behavior
         ensures \result instanceof ValueNode && \fresh(\result)
              && (\result).equals(this);
         ensures_redundantly \result != this; @*/
	
      
    public /*@pure@*/ /*@non_null@*/ Object clone() {
        ValueNode res;
        try {
            res = (ValueNode)super.clone();
        } catch (CloneNotSupportedException e) {
            
            throw new InternalError(e.getMessage());
        }
	return res;
    }

	/*@ 

     public normal_behavior
       assignable this.value;
       ensures this.value == value; @*/
	
      
    public void setValue( /*@nullable@*/ Object value) {
        this.value = value;
    }

	/*@ 

     public normal_behavior
       ensures \result == value; @*/
	
      
    public /*@pure@*/ /*@nullable@*/ Object getValue() {
        return value;
    }


	
  
    public int hashCode() {
        if (value == null) {
            return 0;
        } else {
            return value.hashCode();
        }
    }


	
  
    protected abstract /*@pure@*/ String className();


	

    public /*@non_null@*/ String toString(){
        return className() + "(" + value + ")";     
    }


}