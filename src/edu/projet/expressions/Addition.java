package edu.projet.expressions;

import java.util.HashMap;

import edu.projet.interfaces.DerivationVisitor;
import edu.projet.interfaces.SimplificationVisitor;

public class Addition extends Expression {
	
    public Addition(Expression exprG, Expression exprD) {
		super(exprG, "+", exprD);
	}       
    
	@Override
	public String asString() {
		
		String addition;
		
		if (isZero(this.exprD))
			addition =this.exprG.asString();
		
		else if (isZero(this.exprG))
			addition = this.exprD.asString();
		
		else 
			addition = this.exprG.asString() + " " + this.getSymbole() + " " + this.exprD.asString();
		
		addition = addition.replaceAll("\\+ \\-", "- ");
			
		return addition;
	}
    
	@Override
	public double evaluer(HashMap<String, Double> liste) {
		return  this.exprG.evaluer(liste) + this.exprD.evaluer(liste);
	}
	
	@Override
	public <R> R accept(SimplificationVisitor<R> visitor) {
		try{
			return (visitor).visit(this);
		} 
		catch(ClassCastException exception) {
			return null;
		}
	}
	
	@Override
	public <R> R accept(DerivationVisitor<R> visitor, String dx) {
		try{
			return (visitor).visit(this, dx);
		} 
		catch(ClassCastException exception) {
			return null;
		}
	}

}
