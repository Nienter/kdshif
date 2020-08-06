package p034jp.p035co.cyberagent.android.gpuimage;

/* renamed from: jp.co.cyberagent.android.gpuimage.m */
public class GPUImageGreyMaskFilter extends GPUImageThreeInputFilter {
    public GPUImageGreyMaskFilter() {
        super(" varying highp vec2 textureCoordinate;\n varying highp vec2 textureCoordinate2;\n varying highp vec2 textureCoordinate3;\n\n uniform sampler2D inputImageTexture;\n uniform sampler2D inputImageTexture2;\n uniform sampler2D inputImageTexture3;\n\n void main()\n {\n   lowp vec4 textureColor = texture2D(inputImageTexture, textureCoordinate);\n   lowp vec4 textureColor2 = texture2D(inputImageTexture2, textureCoordinate2);\n   lowp vec4 textureColor3 = texture2D(inputImageTexture3, textureCoordinate3);\n   float percent = (textureColor3.r + textureColor3.g + textureColor3.b) / 3.0;\n\n   gl_FragColor = mix(textureColor, textureColor2, percent);\n }");
    }
}
