# Sample-Application

Gradle File -> Add this 

    //Retrofit
    implementation 'com.squareup.retrofit2:converter-gson:2.1.0'
    //RecyclerView
    implementation 'com.android.support:recyclerview-v7:28.0.0'
    //CardView
    implementation 'com.android.support:cardview-v7:28.0.0'
    
Open your activity and add the following code

    //Our RecyclerView variable
    RecyclerView RecyclerViewMain;
    //LinearLayoutManager variable for handling RecyclerView
    LinearLayoutManager llm1;
    
    RecyclerViewMain = (RecyclerView) findViewById(R.id.RecyclerViewMain);

    llm1 = new LinearLayoutManager(this);
    llm1.setOrientation(LinearLayoutManager.VERTICAL);
    RecyclerViewMain.setLayoutManager(llm1);
    RecyclerViewMain.setHasFixedSize(true);
    
This is the code to fetch data from API

    API apiService = createService(API.class);
    Call<List<MyPojo>> call = apiService.MY_POJO_CALL();
    call.enqueue(new Callback<List<MyPojo>>() {
        @Override
        public void onResponse(Call<List<MyPojo>> call, Response<List<MyPojo>> response) {
            progressBar.dismiss();
            
            //myPojos = new ArrayList<>();
            //myPojos.addAll(response.body());

            //myDB.InsertDataToJsonPlaceHolderTable(response.body());
            recyclerActivityAdapter = new RecyclerActivityAdapter(RecyclerActivity.this, response.body());
            RecyclerViewMain.setAdapter(recyclerActivityAdapter);

        }
        @Override
        public void onFailure(Call<List<MyPojo>> call, Throwable t) {
            progressBar.dismiss();
            Toast.makeText(RecyclerActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
        }
    });
    
Make sure that you have adapter in the following way

    public class RecyclerActivityAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

        private Context context;
        private List<MyPojo> myPojos;
        public RecyclerActivityAdapter(RecyclerActivity recyclerActivity, List<MyPojo> body){
            this.context = recyclerActivity;
            this.myPojos = body;
        }

        private class ViewHolder extends RecyclerView.ViewHolder {
            TextView TextViewTitle;
            private ViewHolder(View itemView) {
                super(itemView);
                TextViewTitle = (TextView) itemView.findViewById(R.id.TextViewTitle);
            }
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            RecyclerView.ViewHolder viewHolder = null;
            View menuItemLayoutView = LayoutInflater.from(context).inflate(
                    R.layout.recycler_card_view, viewGroup, false);
            viewHolder = new ViewHolder(menuItemLayoutView);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            final ViewHolder viewHolder = (ViewHolder) holder;
            viewHolder.TextViewTitle.setText(myPojos.get(position).getTitle());
        }

        @Override
        public int getItemCount() {
            return myPojos.size();
        }
    }
    
Service File be in the following way

    public class Service {

        //https://jsonplaceholder.typicode.com/albums?
        private static final String BASE_URL = "https://jsonplaceholder.typicode.com/";
        private static final OkHttpClient httpClient = new OkHttpClient();

        private static final Retrofit.Builder builder =
                new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create());

        public static  <S> S createService(Class<S> serviceClass) {
            Retrofit retrofit = builder.client(httpClient).build();
            return retrofit.create(serviceClass);
        }

        public static  <S> S createServiceHeader(Class<S> serviceClass) {

            OkHttpClient okClient = new OkHttpClient.Builder()
                    .addInterceptor(
                            new Interceptor() {
                                @Override
                                public Response intercept(Chain chain) throws IOException {
                                    Request original = chain.request();

                                    // Request customization: add request headers
                                    Request.Builder requestBuilder = original.newBuilder()
                                            .header("UserName","e0001")
                                            .method(original.method(), original.body());

                                    Request request = requestBuilder.build();
                                    return chain.proceed(request);
                                }
                            })
                    .build();

            Retrofit retrofit = builder.client(okClient).build();
            return retrofit.create(serviceClass);
        }
    }

API interface be in the following way
    public interface API {

        @GET("albums")
        Call<List<MyPojo>> MY_POJO_CALL();

        //userSignup
    //    @POST("Purohit/register")
    //    Call<SignUpPojo> SIGN_UP_POJO_CALL(@Body JsonObject requestParameter);
    //
    //    //Get Distance from Google Map API
    //    //https://maps.googleapis.com/maps/api/directions/json?origin=13.008906,80.217450&destination=13.008658,80.217836&key=asdfghjklqwer
    //    @GET("directions/json")
    //    Call<GoogleMapDirection> GOOGLE_MAP_DIRECTION_CALL(@Query("origin") String origin, @Query("destination") String destination,
    //                                                       @Query("key") String key);
    }
    
