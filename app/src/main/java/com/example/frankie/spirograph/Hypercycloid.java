package com.example.frankie.spirograph;


import java.util.Random;

public class Hypercycloid {
	Circle inner_circ, outer_circ;
	Position pen_position;
	Random r;
	Color color;
	
	public Hypercycloid(Circle outer, Circle inner, Position pen_position) {
		this.outer_circ = outer;
		this.inner_circ = inner;
		this.pen_position = pen_position;
		this.r = new Random(5);
	}
	
	public float getX(long t) {
		return (float)((outer_circ.radius + inner_circ.radius) * Math.cos(t) - (inner_circ.radius + pen_position.x)*Math.cos(((outer_circ.radius + inner_circ.radius)/inner_circ.radius)*t));
	}
	
	public float getY(long t) {
		return (float)((outer_circ.radius + inner_circ.radius) * Math.sin(t) - (inner_circ.radius + pen_position.x)*Math.sin(((outer_circ.radius + inner_circ.radius)/inner_circ.radius)*t));
	}
	
	public void setInnerRadius(int radius){
		this.inner_circ.radius = radius;
	}
	
	public void setOuterRadius(int radius){
		this.outer_circ.radius = radius;
	}
	
	public void setPenPos(Double pos){
		this.pen_position.x = pos;
	}
	
	public Color getColor(Double t){
		if (this.color != null){
			return new Color((int) (this.color.getR() + Math.cos(t) * 10), (int) (this.color.getG() + Math.cos(t) * 50), (int) (this.color.getB() + Math.cos(t) * 50));
		}
		int rand = r.nextInt(255);
		return new Color(200, 125, 50);
	}
}
