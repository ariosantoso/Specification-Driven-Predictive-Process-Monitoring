#==============================================================================================================
# Neural Network - Classification
#==============================================================================================================

zeroR_clf = DummyClassifier(strategy='most_frequent')

#Callback stuff
earlyStopper = EarlyStopping(monitor='val_loss', patience=70, verbose=1, mode='auto', restore_best_weights=True)
lrReducer = ReduceLROnPlateau(monitor='val_loss', factor=0.5, patience=10, min_lr=0.001)
#model_checkpoint = ModelCheckpoint('./model/weights.{epoch:02d}-{val_loss:.2f}.hdf5', monitor='val_loss', save_best_only=True)
#END OF Callback stuff

#Number of Neuron variants
numNeuron1 = 75
numNeuron2 = 100
numNeuron3 = 150

#Number of Hidden Layers variants
numHL1 = 2
numHL2 = 4
numHL3 = 6

#NN params
parValSplit = 0.2
parActv = 'relu'
parOpt = 'adam'
parEpochs = 2000 #max epochs
parBatchSize = 100
parVerbose = 2
parDropOutRate = 0.3
parDecay = 0.0001
parCallbacks = [earlyStopper, lrReducer]

#notes: nnXY --- X = numHLX --- Y = numNeuronY
nn11 = KerasClassifier(build_fn=nn_clf, validation_split=parValSplit, actv=parActv, opt=parOpt, epochs=parEpochs, batch_size=parBatchSize, verbose=parVerbose, dim=nr_features, k_init=random_normal, dropout_rate=parDropOutRate, hl=numHL1, neurons=numNeuron1, decay=parDecay, callbacks=parCallbacks)

nn12 = KerasClassifier(build_fn=nn_clf, validation_split=parValSplit, actv=parActv, opt=parOpt, epochs=parEpochs, batch_size=parBatchSize, verbose=parVerbose, dim=nr_features, k_init=random_normal, dropout_rate=parDropOutRate, hl=numHL1, neurons=numNeuron2, decay=parDecay, callbacks=parCallbacks)

nn13 = KerasClassifier(build_fn=nn_clf, validation_split=parValSplit, actv=parActv, opt=parOpt, epochs=parEpochs, batch_size=parBatchSize, verbose=parVerbose, dim=nr_features, k_init=random_normal, dropout_rate=parDropOutRate, hl=numHL1, neurons=numNeuron3, decay=parDecay, callbacks=parCallbacks)

#------------------------------------------------

nn21 = KerasClassifier(build_fn=nn_clf, validation_split=parValSplit, actv=parActv, opt=parOpt, epochs=parEpochs, batch_size=parBatchSize, verbose=parVerbose, dim=nr_features, k_init=random_normal, dropout_rate=parDropOutRate, hl=numHL2, neurons=numNeuron1, decay=parDecay, callbacks=parCallbacks)

nn22 = KerasClassifier(build_fn=nn_clf, validation_split=parValSplit, actv=parActv, opt=parOpt, epochs=parEpochs, batch_size=parBatchSize, verbose=parVerbose, dim=nr_features, k_init=random_normal, dropout_rate=parDropOutRate, hl=numHL2, neurons=numNeuron2, decay=parDecay, callbacks=parCallbacks)

nn23 = KerasClassifier(build_fn=nn_clf, validation_split=parValSplit, actv=parActv, opt=parOpt, epochs=parEpochs, batch_size=parBatchSize, verbose=parVerbose, dim=nr_features, k_init=random_normal, dropout_rate=parDropOutRate, hl=numHL2, neurons=numNeuron3, decay=parDecay, callbacks=parCallbacks)

#------------------------------------------------

nn31 = KerasClassifier(build_fn=nn_clf, validation_split=parValSplit, actv=parActv, opt=parOpt, epochs=parEpochs, batch_size=parBatchSize, verbose=parVerbose, dim=nr_features, k_init=random_normal, dropout_rate=parDropOutRate, hl=numHL3, neurons=numNeuron1, decay=parDecay, callbacks=parCallbacks)

nn32 = KerasClassifier(build_fn=nn_clf, validation_split=parValSplit, actv=parActv, opt=parOpt, epochs=parEpochs, batch_size=parBatchSize, verbose=parVerbose, dim=nr_features, k_init=random_normal, dropout_rate=parDropOutRate, hl=numHL3, neurons=numNeuron2, decay=parDecay, callbacks=parCallbacks)

nn33 = KerasClassifier(build_fn=nn_clf, validation_split=parValSplit, actv=parActv, opt=parOpt, epochs=parEpochs, batch_size=parBatchSize, verbose=parVerbose, dim=nr_features, k_init=random_normal, dropout_rate=parDropOutRate, hl=numHL3, neurons=numNeuron3, decay=parDecay, callbacks=parCallbacks)


model11 = ('nn11 hl='+str(numHL1)+',n='+str(numNeuron1),nn11)
model12 = ('nn12 hl='+str(numHL1)+',n='+str(numNeuron2),nn12)
model13 = ('nn13 hl='+str(numHL1)+',n='+str(numNeuron3),nn13)

model21 = ('nn21 hl='+str(numHL2)+',n='+str(numNeuron1),nn21)
model22 = ('nn22 hl='+str(numHL2)+',n='+str(numNeuron2),nn22)
model23 = ('nn23 hl='+str(numHL2)+',n='+str(numNeuron3),nn23)

model31 = ('nn31 hl='+str(numHL3)+',n='+str(numNeuron1),nn31)
model32 = ('nn32 hl='+str(numHL3)+',n='+str(numNeuron2),nn32)
model33 = ('nn33 hl='+str(numHL3)+',n='+str(numNeuron3),nn33)

models = [('ZeroR',zeroR_clf), model11, model12, model13, model21, model22, model23, model31, model32, model33]

test_list = [('TEST WITH TRAINING INSTANCE', training, target),('PREDICTION FOR ALL POSSIBLE PREFIXES', all_test_data, all_target)]

#==============================================================================================================
# Neural Network - Classification
#==============================================================================================================






