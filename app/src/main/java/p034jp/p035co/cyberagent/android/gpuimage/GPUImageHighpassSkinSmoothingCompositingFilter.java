package p034jp.p035co.cyberagent.android.gpuimage;

/* renamed from: jp.co.cyberagent.android.gpuimage.p */
public class GPUImageHighpassSkinSmoothingCompositingFilter extends GPUImageThreeInputFilter {
    public GPUImageHighpassSkinSmoothingCompositingFilter() {
        super(" varying highp vec2 textureCoordinate;\n varying highp vec2 textureCoordinate2;\n varying highp vec2 textureCoordinate3;\n\n uniform sampler2D inputImageTexture;\n uniform sampler2D inputImageTexture2;\n uniform sampler2D inputImageTexture3;\n\n void main()\n {\n   lowp vec4 image = texture2D(inputImageTexture, textureCoordinate);\n   lowp vec4 toneCurvedImage = texture2D(inputImageTexture2, textureCoordinate2);\n   lowp vec4 mask = texture2D(inputImageTexture3, textureCoordinate3);\n   highp float mixValue = 1.0 - (mask.r + mask.g + mask.b) / 3.0; \n   gl_FragColor = mix(image, toneCurvedImage, mixValue); \n }");
    }
}
