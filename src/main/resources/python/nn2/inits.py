seed = 17
import os
os.environ['PYTHONHASHSEED'] = '0'
import numpy as np
import random as rn
# import tensorflow as tf
np.random.seed(seed)
rn.seed(seed)
# tf.set_random_seed(seed)
# session_conf = tf.ConfigProto(intra_op_parallelism_threads=1, inter_op_parallelism_threads=1)
# from keras import backend as K
# sess = tf.Session(graph=tf.get_default_graph(), config=session_conf)
# K.set_session(sess)
from sklearn import metrics
from sklearn.metrics import classification_report

from sklearn.model_selection import RandomizedSearchCV, GridSearchCV
from sklearn.externals.joblib import parallel_backend

#For classification
from sklearn.dummy import DummyClassifier
from sklearn.linear_model import LogisticRegression
from sklearn.naive_bayes import GaussianNB
from sklearn.tree import DecisionTreeClassifier
from sklearn.ensemble import GradientBoostingClassifier
from sklearn.ensemble import RandomForestClassifier
from sklearn.ensemble import AdaBoostClassifier
from sklearn.ensemble import ExtraTreesClassifier
from sklearn.ensemble import VotingClassifier
from keras.wrappers.scikit_learn import KerasClassifier

#For regression
from sklearn.dummy import DummyRegressor
from sklearn.linear_model import LinearRegression
from sklearn.tree import DecisionTreeRegressor
from sklearn.ensemble import GradientBoostingRegressor
from sklearn.ensemble import RandomForestRegressor
from sklearn.ensemble import AdaBoostRegressor
from sklearn.ensemble import ExtraTreesRegressor
from keras.wrappers.scikit_learn import KerasRegressor

#Keras Stuff
import keras
from keras.models import Sequential
from keras.layers import Dense, LSTM, Dropout, Activation, BatchNormalization
from keras.layers.embeddings import Embedding
from keras.preprocessing import sequence
from keras.optimizers import SGD, Adam, Adadelta
from keras.initializers import random_normal
from keras.callbacks import EarlyStopping, ModelCheckpoint, ReduceLROnPlateau

from math import sqrt


import pickle




