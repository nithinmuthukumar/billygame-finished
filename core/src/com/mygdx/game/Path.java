package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Choices.Choice;

public class Path {
    public Vector2[] points;
    public Path(Vector2 start){
        generate(start,300,800);


    }



    public void draw(ShapeRenderer renderer){
        Color c=renderer.getColor().cpy();
        for(int i=0;i<points.length-1;i++) {

            renderer.setColor(1,0,0,1);
            renderer.line(points[i].cpy().add(0,2.5f),points[i+1].cpy().add(0,2.5f));
            renderer.line(points[i],points[i+1]);
            renderer.setColor(c);

            renderer.rectLine(points[i],points[i+1],5);

        }
    }
    //give them an inclination
    private void generate(Vector2 start,int numPoints,int dist){
        //have a randomness that isnt so volatile and is close to previous lines angle
        points=new Vector2[numPoints];

        points[0]=start;
        int sp=1;
        int prevAngle=360;
        for(; sp<numPoints;sp++){
            Vector2 point=new Vector2(dist/numPoints,0);

            prevAngle=MathUtils.random(prevAngle-4,prevAngle+4);
            if (prevAngle < 270) {
                prevAngle=270;
            }else if(prevAngle>450){
                prevAngle=450;
            }
            point.setAngle(prevAngle);
            points[sp]=point.add(points[sp-1]);

        }
    }



    public Vector2 getEnd() {
        return points[points.length-1];
    }
}
