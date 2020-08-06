package p034jp.p035co.cyberagent.android.gpuimage;

/* renamed from: jp.co.cyberagent.android.gpuimage.o */
public class GPUImageHighPassFilter extends GPUImageTwoInputFilter {
    public GPUImageHighPassFilter() {
        super("varying highp vec2 textureCoordinate;\n varying highp vec2 textureCoordinate2;\n\n uniform sampler2D inputImageTexture;\n uniform sampler2D inputImageTexture2;\n \n uniform lowp float mixturePercent;\n\n void main()\n {\n    vec4 image = texture2D(inputImageTexture, textureCoordinate); \n    vec4 blurredImage = texture2D(inputImageTexture2, textureCoordinate); \n    gl_FragColor = vec4((image.rgb - blurredImage.rgb + vec3(0.5,0.5,0.5)), image.a); \n }");
    }
}
