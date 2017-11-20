package com.example.frankie.spirograph;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;


public class SpiroView extends View{
	private Hypercycloid hp;
	private Context context;
	private Paint paint;
	private Color paintColor;
	private Bitmap bitmap;
	private Canvas canvas;
	private long delta;
	private int dotCount;
	private int VIEW_WIDTH;
	private int VIEW_HEIGHT;
	private Runnable updateFrame;
	private Handler frameHandler;
	private int animationSpeed;

	
	public SpiroView(Context c, AttributeSet attrs){
		super(c, attrs);

		this.context = c;
		this.paint = new Paint(Paint.ANTI_ALIAS_FLAG);
		this.paint.setARGB(255, 110, 170, 200);
		this.paint.setStyle(Paint.Style.FILL);
		this.paint.setStrokeWidth(10f);
		this.paintColor = new Color(paint.getColor());
		this.dotCount = 200;
		this.animationSpeed = 200;

		/*this.hp = hp;

		this.update(hp);*/

		updateFrame = new Runnable() {
			@Override
			public void run() {
				Log.d("MyApp", "RUN!!");
				invalidate();
				frameHandler.postDelayed(this, animationSpeed);
			}
		};
		frameHandler = new android.os.Handler();

	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);

		this.bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
		VIEW_WIDTH = w;
		VIEW_HEIGHT = h;
		this.canvas = new Canvas(bitmap);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		long now = System.currentTimeMillis();
		for (int i = 0; i < dotCount; i++){
			canvas.drawPoint(VIEW_WIDTH /2 + hp.getX(now - i), VIEW_HEIGHT /2 + hp.getY(now - i), paint);
		}
		//canvas.drawLine(10, 10, 50, 100, paint);
		delta = now;
		Log.d("MyApp", "Just redrew the hypercycloid");
	}

	public void update(Hypercycloid hp){
		this.hp = hp;
		this.delta = System.currentTimeMillis();
	}

	public void setDotCount(int count){
		this.dotCount = count;
		this.invalidate();
	}

	public void stopAnimation(){
		frameHandler.removeCallbacks(updateFrame);
	}

	public void startAnimation(){
		frameHandler.postDelayed(updateFrame, animationSpeed);
	}

	public void setAnimationSpeed(int speed){
		this.animationSpeed = speed;
	}

	public int getAnimationSpeed(){
		return this.animationSpeed;
	}

	public int getDotCount(){
		return this.dotCount;
	}
	/*
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
	
		for (Double t = 0.0; t < 100; t = t + 0.01) {
			Ellipse2D.Double circle = new Ellipse2D.Double(hp.getX(t) + hp.outer_circ.position.x, hp.getY(t) + hp.outer_circ.position.y, 3, 3);
			g2d.setColor(hp.getColor(t));
			g2d.fill(circle);
		}
	}*/
	
	
}
