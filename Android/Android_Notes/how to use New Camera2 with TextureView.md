# how to use New Camera2 with TextureView

## MainActivity.java

	public class MainActivity extends AppCompatActivity
        implements TextureView.SurfaceTextureListener {

	    private TextureView mPreviewView;
	    private HandlerThread mThreadHandler;
	    private Handler mHandler;
	    private CaptureRequest.Builder mPreviewBuilder;
	
	    @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_main);
	        mPreviewView = (TextureView) findViewById(R.id.textureview);
	        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) !=
	                PackageManager.PERMISSION_GRANTED) {
	
	            ActivityCompat.requestPermissions(this,
	                    new String[]{Manifest.permission.CAMERA}, 101);
	            return;
	        }
	    }
	
	    public void btn(View v) {
	        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) !=
	                PackageManager.PERMISSION_GRANTED) {
	
	            ActivityCompat.requestPermissions(this,
	                    new String[]{Manifest.permission.CAMERA}, 101);
	            return;
	        }
	        mThreadHandler = new HandlerThread("CAMERA2");
	        mThreadHandler.start();
	        mHandler = new Handler(mThreadHandler.getLooper());
	
	        mPreviewView.setSurfaceTextureListener(this);
	    }
	
	    @Override
	    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
	        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
	        switch (requestCode) {
	            case 101:
	                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
	                    Toast.makeText(this, "yes", Toast.LENGTH_SHORT).show();
	                } else {
	                    Toast.makeText(this, "no", Toast.LENGTH_SHORT).show();
	                }
	                break;
	        }
	    }
	
	    //TextureView-----------------
	    @Override
	    public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
	        CameraManager cameraManager = (CameraManager) getSystemService(CAMERA_SERVICE);
	        try {
	            String[] CameraIdList = cameraManager.getCameraIdList();
	            //获取可用相机设备列表
	            CameraCharacteristics characteristics = cameraManager.getCameraCharacteristics
	                    (CameraIdList[0]);
	            //在这里可以通过CameraCharacteristics设置相机的功能,当然必须检查是否支持
		//            characteristics.get(CameraCharacteristics.INFO_SUPPORTED_HARDWARE_LEVEL);
	            //就像这样
	            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) !=
	                    PackageManager.PERMISSION_GRANTED) {
	
	                ActivityCompat.requestPermissions(this,
	                        new String[]{Manifest.permission.CAMERA}, 101);
	                return;
	            }
	            cameraManager.openCamera(CameraIdList[0], mStateCallback, mHandler);
	        } catch (CameraAccessException e) {
	            e.printStackTrace();
	        }
	    }
	
	    @Override
	    public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {
	
	    }
	
	    @Override
	    public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
	        return false;
	    }
	
	    @Override
	    public void onSurfaceTextureUpdated(SurfaceTexture surface) {
	
	    }
	    //TextureView-----------------
	
	    //CameraDevice.StateCallback-----------------
	    CameraDevice.StateCallback mStateCallback = new CameraDevice.StateCallback() {
	        @Override
	        public void onOpened(CameraDevice camera) {
	            try {
	                startPreview(camera);
	            } catch (CameraAccessException e) {
	                e.printStackTrace();
	            }
	        }
	
	        @Override
	        public void onDisconnected(CameraDevice camera) {
	
	        }
	
	        @Override
	        public void onError(CameraDevice camera, int error) {
	
	        }
	    };
	    //CameraDevice.StateCallback-----------------
	
	    //CameraCaptureSession.StateCallback-----------------
	    CameraCaptureSession.StateCallback mStateCallback2 = new CameraCaptureSession.StateCallback() {
	
	        @Override
	        public void onConfigured(CameraCaptureSession session) {
	            try {
	                session.setRepeatingRequest(mPreviewBuilder.build(), mCaptureCallback, mHandler);
	            } catch (CameraAccessException e) {
	                e.printStackTrace();
	            }
	        }
	
	        @Override
	        public void onConfigureFailed(CameraCaptureSession session) {
	
	        }
	    };
	    //CameraCaptureSession.StateCallback-----------------
	
	    // CaptureCallback-----------------
	    CameraCaptureSession.CaptureCallback mCaptureCallback = new CameraCaptureSession
	            .CaptureCallback() {
	        @Override
	        public void onCaptureSequenceAborted(CameraCaptureSession session, int sequenceId) {
	            super.onCaptureSequenceAborted(session, sequenceId);
	        }
	
	        @Override
	        public void onCaptureSequenceCompleted(CameraCaptureSession session, int sequenceId, long
	                frameNumber) {
	            super.onCaptureSequenceCompleted(session, sequenceId, frameNumber);
	        }
	
	        @Override
	        public void onCaptureFailed(CameraCaptureSession session, CaptureRequest request,
	                                    CaptureFailure failure) {
	            super.onCaptureFailed(session, request, failure);
	        }
	
	        @Override
	        public void onCaptureCompleted(CameraCaptureSession session, CaptureRequest request,
	                                       TotalCaptureResult result) {
	            super.onCaptureCompleted(session, request, result);
	        }
	
	        @Override
	        public void onCaptureProgressed(CameraCaptureSession session, CaptureRequest request,
	                                        CaptureResult partialResult) {
	            super.onCaptureProgressed(session, request, partialResult);
	            Log.i("huanghaiqi", "huanghaiqi");
	        }
	
	        @Override
	        public void onCaptureStarted(CameraCaptureSession session, CaptureRequest request, long
	                timestamp, long frameNumber) {
	            super.onCaptureStarted(session, request, timestamp, frameNumber);
	        }
	    };
	    //CaptureCallback-----------------
	
	    private void startPreview(CameraDevice camera) throws CameraAccessException {
	        SurfaceTexture texture = mPreviewView.getSurfaceTexture();
	        texture.setDefaultBufferSize(mPreviewView.getWidth(), mPreviewView.getHeight());
	        Surface surface = new Surface(texture);
	        try {
	            mPreviewBuilder = camera.createCaptureRequest(CameraDevice
	                    .TEMPLATE_STILL_CAPTURE);
	        } catch (CameraAccessException e) {
	            e.printStackTrace();
	        }
	        mPreviewBuilder.addTarget(surface);
	        camera.createCaptureSession(Arrays.asList(surface), mStateCallback2, mHandler);
	    }
	}

