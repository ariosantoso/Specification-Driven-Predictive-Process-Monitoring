def nn_reg(actv='relu', opt='adam', k_init=keras.initializers.random_normal, dropout_rate=0.2, dim=100, neurons=250, hl=3, amsgrad=False, decay=0.0):
    
    nn_model = Sequential()
    
    #creating the input layer and one hidden layer    
    nn_model.add(Dense(neurons, input_dim=dim, kernel_initializer=k_init(seed=seed)))
    nn_model.add(BatchNormalization())
    nn_model.add(Activation(actv))
    nn_model.add(Dropout(dropout_rate, seed=seed))

    #adding the hidden layers
    for ii in range (1, hl):
        nn_model.add(Dense(neurons, kernel_initializer=k_init(seed=seed)))
        nn_model.add(BatchNormalization())
        nn_model.add(Activation(actv))
        nn_model.add(Dropout(dropout_rate, seed=seed))

    #adding the output layer
    nn_model.add(Dense(1, kernel_initializer=k_init(seed=seed)))

    optimizer = keras.optimizers.Adam(amsgrad=amsgrad, decay=decay)
    nn_model.compile(loss='mean_squared_error', optimizer=optimizer, metrics=['mean_squared_error', 'mean_absolute_error', 'mean_absolute_percentage_error'])
    
    return nn_model

