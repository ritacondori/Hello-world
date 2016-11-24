package com.aiconoa.slideshow.helper;

import com.aiconoa.slideshow.entity.ImageSlideElement;
import com.aiconoa.slideshow.entity.SlideElement;
import com.aiconoa.slideshow.entity.TextSlideElement;

public class SlideElementRenderer {

	public static String renderHTML(SlideElement<?> se) {
        if(se instanceof TextSlideElement) {
            TextSlideElement e = (TextSlideElement) se;
            return String.format("<text text-anchor=\"middle\" alignment-baseline=\"middle\" x=\"%f\"  y=\"%f\" font-family=\"Helvetica\" font-size=\"40\"  fill=\"black\">%s</text>", e.getX(), e.getY(), e.getContent());                    
        } else if (se instanceof ImageSlideElement) {
            ImageSlideElement e = (ImageSlideElement) se;            
            return String.format("<image xlink:href=\"/img/%s\" x=\"%f\" y=\"%f\" width=\"%f\" height=\"%f\" preserveAspectRatio=\"xMidYMid meet\"/>", e.getContent(), e.getX(), e.getY(), e.getWidth(), e.getHeight());
        }
        
        return "";
	}
}
